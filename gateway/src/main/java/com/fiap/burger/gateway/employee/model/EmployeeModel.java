package com.fiap.burger.gateway.employee.model;

import com.fiap.burger.entity.employee.Employee;
import com.fiap.burger.entity.employee.EmployeeType;
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbAttribute;
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbBean;
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbPartitionKey;

import java.util.Objects;

@DynamoDbBean
public class EmployeeModel {
    private String id;
    private String name;
    private String email;
    private EmployeeType type;
    private String password;

    public EmployeeModel() {

    }

    public EmployeeModel(Employee employee) {
        this.id = employee.getId();
        this.name = employee.getName();
        this.email = employee.getEmail();
        this.type = employee.getType();
        this.password = employee.getPassword();
    }

    @DynamoDbPartitionKey
    @DynamoDbAttribute("id")
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @DynamoDbAttribute("name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @DynamoDbAttribute("email")
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @DynamoDbAttribute("type")
    public EmployeeType getType() {
        return type;
    }

    public void setType(EmployeeType type) {
        this.type = type;
    }

    @DynamoDbAttribute("password")
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        EmployeeModel that = (EmployeeModel) o;
        return Objects.equals(id, that.id) && Objects.equals(name, that.name) && Objects.equals(email, that.email) && type == that.type && Objects.equals(password, that.password);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, email, type, password);
    }

    @Override
    public String toString() {
        return "EmployeeModel{" +
            "id='" + id + '\'' +
            ", name='" + name + '\'' +
            ", email='" + email + '\'' +
            ", type=" + type +
            ", password='" + password + '\'' +
            '}';
    }

    public Employee toEntity() {
        return new Employee(
            id,
            name,
            email,
            type,
            password
        );
    }
}
