package com.fiap.burger.gateway.customer.model;


import java.time.LocalDateTime;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;

import com.fiap.burger.entity.customer.Customer;
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbAttribute;
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbBean;
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbPartitionKey;
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbSecondaryPartitionKey;
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbSecondarySortKey;

@DynamoDbBean
public class CustomerModel {

    private String id;
    private String cpf;
    private String email;
    private String name;
    private LocalDateTime createdAt;
    private LocalDateTime modifiedAt;
    private LocalDateTime deletedAt;

    public CustomerModel() {}

    public CustomerModel(Customer customer) {
        this.id = Optional.ofNullable(customer.getId()).orElse(UUID.randomUUID().toString());
        this.cpf = customer.getCpf();
        this.email = customer.getEmail();
        this.name = customer.getName();
        this.createdAt = customer.getCreatedAt();
        this.modifiedAt = customer.getModifiedAt();
        this.deletedAt = customer.getDeletedAt();
    }

    @DynamoDbPartitionKey
    @DynamoDbAttribute("id")
    @DynamoDbSecondarySortKey(indexNames = "cpf")
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @DynamoDbSecondaryPartitionKey(indexNames = "cpf")
    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    @DynamoDbAttribute("email")
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @DynamoDbAttribute("name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @DynamoDbAttribute("created_at")
    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    @DynamoDbAttribute("modified_at")
    public LocalDateTime getModifiedAt() {
        return modifiedAt;
    }

    public void setModifiedAt(LocalDateTime modifiedAt) {
        this.modifiedAt = modifiedAt;
    }

    @DynamoDbAttribute("deleted_at")
    public LocalDateTime getDeletedAt() {
        return deletedAt;
    }

    public void setDeletedAt(LocalDateTime deletedAt) {
        this.deletedAt = deletedAt;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof CustomerModel that)) return false;
        return Objects.equals(getId(), that.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }

    @Override
    public String toString() {
        return "CustomerModel{" +
                "id=" + id +
                ", cpf='" + cpf + '\'' +
                ", email='" + email + '\'' +
                ", name='" + name + '\'' +
                ", createdAt=" + createdAt +
                ", modifiedAt=" + modifiedAt +
                ", deletedAt=" + deletedAt +
                '}';
    }

    public Customer toEntity() {
        return new Customer(
                id,
                cpf,
                email,
                name,
                createdAt,
                modifiedAt,
                deletedAt
        );
    }
}

