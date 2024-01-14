package com.fiap.burger.gateway.customercpf.gateway;

import com.fiap.burger.gateway.customercpf.model.CustomerCpfModel;
import com.fiap.burger.usecase.adapter.gateway.CustomerCpfGateway;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;
import software.amazon.awssdk.enhanced.dynamodb.DynamoDbEnhancedClient;
import software.amazon.awssdk.enhanced.dynamodb.DynamoDbTable;
import software.amazon.awssdk.enhanced.dynamodb.Key;
import software.amazon.awssdk.enhanced.dynamodb.TableSchema;

@Repository
public class DefaultCustomerCpfGateway implements CustomerCpfGateway {

    private DynamoDbEnhancedClient enhancedCustomer;
    private DynamoDbTable<CustomerCpfModel> table;

    DefaultCustomerCpfGateway(DynamoDbEnhancedClient enhancedCustomer, @Value("${dynamodb.tablename}") String tableName) {
        this.enhancedCustomer = enhancedCustomer;
        this.table = enhancedCustomer.table(tableName, TableSchema.fromBean(CustomerCpfModel.class));
    }
    @Override
    public void save(String cpf, Long customerId) {
        table.putItem(new CustomerCpfModel(cpf, customerId));
    }

    @Override
    public Long findByCpf(String cpf) {
        return table.getItem(Key.builder().partitionValue(cpf).build()).getId();
    }


}
