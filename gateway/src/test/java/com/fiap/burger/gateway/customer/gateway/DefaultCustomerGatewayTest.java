package com.fiap.burger.gateway.customer.gateway;

import com.fiap.burger.usecase.adapter.gateway.CustomerCpfGateway;
import org.junit.jupiter.api.BeforeEach;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.assertEquals;

class DefaultCustomerGatewayTest {

//    @Mock
//    CustomerDAO customerDAO;

    @Mock
    CustomerCpfGateway customerCpfGateway;

    @InjectMocks
    DefaultCustomerGateway gateway;

    @BeforeEach
    void setup() {
        MockitoAnnotations.openMocks(this);
    }

//    @Test
//    void shouldFindById() {
//        var id = 1L;
//        var customerJPA = new CustomerJPABuilder().withId(1L).build();
//        var expected = customerJPA.toEntity();
//
//        when(customerDAO.findById(id)).thenReturn(Optional.of(customerJPA));
//
//        var actual = gateway.findById(id);
//
//        assertEquals(expected, actual);
//
//        verify(customerDAO, times(1)).findById(id);
//    }
//
//    @Test
//    void shouldFindByCpf() {
//        var cpf = "12345678909";
//        var customerJPA = new CustomerJPABuilder().withId(1L).build();
//        var expected = customerJPA.toEntity();
//
//        when(customerDAO.findByCpf(cpf)).thenReturn(Optional.of(customerJPA));
//
//        var actual = gateway.findByCpf(cpf);
//
//        assertEquals(expected, actual);
//
//        verify(customerDAO, times(1)).findByCpf(cpf);
//    }
//
//    @Test
//    void shouldSaveCustomer() {
//        var customerJPA = new CustomerJPABuilder().withId(1L).build();
//        var customer = new CustomerBuilder().withId(null).build();
//        var expected = new CustomerBuilder().withId(1L).build();
//
//        when(customerDAO.save(any())).thenReturn(customerJPA);
//        doNothing().when(customerCpfGateway).save(expected.getCpf(), expected.getId());
//
//        var actual = gateway.save(customer);
//
//        assertEquals(expected.getId(), actual.getId());
//
//        verify(customerDAO, times(1)).save(any());
//    }
}
