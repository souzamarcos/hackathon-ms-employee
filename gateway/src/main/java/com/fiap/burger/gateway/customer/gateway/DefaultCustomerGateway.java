package com.fiap.burger.gateway.customer.gateway;

import com.fiap.burger.entity.customer.Customer;
import com.fiap.burger.gateway.customer.model.CustomerModel;
import com.fiap.burger.usecase.adapter.gateway.CustomerGateway;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;
import software.amazon.awssdk.enhanced.dynamodb.DynamoDbEnhancedClient;
import software.amazon.awssdk.enhanced.dynamodb.DynamoDbTable;
import software.amazon.awssdk.enhanced.dynamodb.TableSchema;

@Repository
public class DefaultCustomerGateway implements CustomerGateway {

    private DynamoDbEnhancedClient enhancedCustomer;
    private DynamoDbTable<CustomerModel> table;

    DefaultCustomerGateway(DynamoDbEnhancedClient enhancedCustomer, @Value("${dynamodb.tablename}") String tableName) {
        this.enhancedCustomer = enhancedCustomer;
        this.table = enhancedCustomer.table(tableName, TableSchema.fromBean(CustomerModel.class));
    }

    @Override
    public Customer findById(Long id) {
        return null;
    }

    @Override
    public Customer save(Customer customer) {
        // table.putItem(new CustomerModel(customer));
        return null;
    }

    @Override
    public Customer findByCpf(String cpf) {
        return null;
    }


}
