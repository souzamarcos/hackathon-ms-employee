package com.fiap.hackathon.gateway.employee.gateway;

import com.fiap.hackathon.entity.employee.Employee;
import com.fiap.hackathon.gateway.employee.model.EmployeeModel;
import com.fiap.hackathon.usecase.adapter.gateway.EmployeeGateway;
import org.springframework.beans.factory.annotation.Autowired;
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

    public DefaultEmployeeGateway(@Autowired DynamoDbEnhancedClient enhancedEmployee, @Value("${dynamodb.tablename}") String tableName) {
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
