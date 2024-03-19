package com.fiap.burger.gateway.employee.gateway;

import com.fiap.burger.entity.employee.Employee;
import com.fiap.burger.gateway.employee.model.EmployeeModel;
import com.fiap.burger.usecase.adapter.gateway.EmployeeGateway;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;
import software.amazon.awssdk.enhanced.dynamodb.DynamoDbEnhancedClient;
import software.amazon.awssdk.enhanced.dynamodb.DynamoDbTable;
import software.amazon.awssdk.enhanced.dynamodb.Key;
import software.amazon.awssdk.enhanced.dynamodb.TableSchema;

import java.util.Optional;

@Repository
public class DefaultEmployeeGateway implements EmployeeGateway {
    private final DynamoDbTable<EmployeeModel> table;

    public DefaultEmployeeGateway(DynamoDbEnhancedClient enhancedEmployee, @Value("${dynamodb.employee.tablename}") String tableName) {
        this.table = enhancedEmployee.table(tableName, TableSchema.fromBean(EmployeeModel.class));
    }

    @Override
    public Employee findById(String id) {
        return Optional.ofNullable(table.getItem(Key.builder().partitionValue(id).build()))
            .map(EmployeeModel::toEntity)
            .orElse(null);
    }

    @Override
    public Employee save(Employee employee) {
        var newEmployee = new EmployeeModel(employee);
        table.putItem(newEmployee);
        return newEmployee.toEntity();
    }
}
