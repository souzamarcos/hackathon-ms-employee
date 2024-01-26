package com.fiap.burger.gateway.customer.gateway;

import com.fiap.burger.entity.customer.Customer;
import com.fiap.burger.gateway.customer.model.CustomerModel;
import com.fiap.burger.usecase.adapter.gateway.CustomerGateway;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;
import software.amazon.awssdk.enhanced.dynamodb.*;
import software.amazon.awssdk.enhanced.dynamodb.model.PageIterable;
import software.amazon.awssdk.enhanced.dynamodb.model.QueryConditional;
import software.amazon.awssdk.enhanced.dynamodb.model.QueryEnhancedRequest;

@Repository
public class DefaultCustomerGateway implements CustomerGateway {

    private DynamoDbEnhancedClient enhancedCustomer;
    private DynamoDbTable<CustomerModel> table;

    DefaultCustomerGateway(DynamoDbEnhancedClient enhancedCustomer, @Value("${dynamodb.tablename}") String tableName) {
        this.enhancedCustomer = enhancedCustomer;
        this.table = enhancedCustomer.table(tableName, TableSchema.fromBean(CustomerModel.class));
    }

    @Override
    public Customer findById(String id) {
        QueryConditional queryConditional = QueryConditional
            .keyEqualTo(Key.builder().partitionValue(id).
                build());

        PageIterable<CustomerModel>  pages = table.query(QueryEnhancedRequest.builder().queryConditional(queryConditional).build());
        return pages.items().stream().findFirst().map(CustomerModel::toEntity).orElse(null);
    }

    @Override
    public Customer save(Customer customer) {
        var newCustomer = new CustomerModel(customer);
        table.putItem(newCustomer);
        return newCustomer.toEntity();
    }

    @Override
    public Customer findByCpf(String cpf) {
        DynamoDbIndex<CustomerModel> secIndex = table.index("id-cpf");

        QueryConditional queryConditional = QueryConditional
            .keyEqualTo(Key.builder().partitionValue(cpf).
                build());

        PageIterable<CustomerModel>  pages = ( (PageIterable<CustomerModel>) secIndex.query(QueryEnhancedRequest.builder().queryConditional(queryConditional).build()) );
        return pages.items().stream().findFirst().map(CustomerModel::toEntity).orElse(null);
    }

}
