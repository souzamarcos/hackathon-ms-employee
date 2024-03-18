package com.fiap.burger.gateway.customer.gateway;

import com.fiap.burger.gateway.customer.model.CustomerModel;
import com.fiap.burger.gateway.misc.CustomerBuilder;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import software.amazon.awssdk.core.pagination.sync.SdkIterable;
import software.amazon.awssdk.enhanced.dynamodb.DynamoDbEnhancedClient;
import software.amazon.awssdk.enhanced.dynamodb.DynamoDbIndex;
import software.amazon.awssdk.enhanced.dynamodb.DynamoDbTable;
import software.amazon.awssdk.enhanced.dynamodb.Key;
import software.amazon.awssdk.enhanced.dynamodb.TableSchema;
import software.amazon.awssdk.enhanced.dynamodb.model.Page;
import software.amazon.awssdk.enhanced.dynamodb.model.PageIterable;
import software.amazon.awssdk.enhanced.dynamodb.model.QueryEnhancedRequest;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

class DefaultCustomerGatewayTest {

    @Mock
    DynamoDbEnhancedClient enhancedCustomer;

    @Mock
    DynamoDbTable<CustomerModel> table;

    DefaultCustomerGateway gateway;

    @BeforeEach
    void setup() {
        MockitoAnnotations.openMocks(this);
        when(enhancedCustomer.table("tf-customers-table", TableSchema.fromBean(CustomerModel.class))).thenReturn(table);
        gateway = new DefaultCustomerGateway(enhancedCustomer, "tf-customers-table");
    }

    @Test
    void shouldFindById() {
        var customer = new CustomerBuilder().build();
        var customerModel = new CustomerModel(customer);
        var id = customerModel.getId();

        Page<CustomerModel> page = Page.builder(CustomerModel.class).items(Arrays.asList(customerModel)).build();

        SdkIterable sdkIterableMock = mock(SdkIterable.class);
        when(sdkIterableMock.stream()).thenReturn(page.items().stream());

        PageIterable pageIterableMock = mock(PageIterable.class);
        when(pageIterableMock.items()).thenReturn(sdkIterableMock);

        when(table.query(any(QueryEnhancedRequest.class))).thenReturn(pageIterableMock);

        var actual = gateway.findById(id);

        assertEquals(id, actual.getId());

        verify(table, times(1)).query(any(QueryEnhancedRequest.class));
    }

    @Test
    void shouldFindByCpf() {
        var cpf = "12345678901";
        var customer = new CustomerBuilder().withCpf(cpf).build();
        var customerModel = new CustomerModel(customer);

        Page<CustomerModel> page = Page.builder(CustomerModel.class).items(Arrays.asList(customerModel)).build();

        SdkIterable sdkIterableMock = mock(SdkIterable.class);
        when(sdkIterableMock.stream()).thenReturn(page.items().stream());

        PageIterable pageIterableMock = mock(PageIterable.class);
        when(pageIterableMock.items()).thenReturn(sdkIterableMock);

        DynamoDbIndex<CustomerModel> secIndex = mock(DynamoDbIndex.class);

        when(table.index("cpf")).thenReturn(secIndex);
        when(secIndex.query(any(QueryEnhancedRequest.class))).thenReturn(pageIterableMock);

        var actual = gateway.findByCpf(cpf);

        assertEquals(cpf, actual.getCpf());

        verify(table, never()).query(any(QueryEnhancedRequest.class));
        verify(secIndex, times(1)).query(any(QueryEnhancedRequest.class));
    }

    @Test
    void shouldSaveCustomer() {
        var customer = new CustomerBuilder().withId(null).build();
        var actual = gateway.save(customer);

        assertNotNull(actual.getId());
        assertEquals(customer.getCpf(), actual.getCpf());

        verify(table, times(1)).putItem(any(CustomerModel.class));
    }

    @Test
    void shouldDeleteCustomer() {
        var id = "id";

        gateway.deleteById(id);

        verify(table, times(1)).deleteItem(Key.builder().partitionValue(id).build());
    }


}
