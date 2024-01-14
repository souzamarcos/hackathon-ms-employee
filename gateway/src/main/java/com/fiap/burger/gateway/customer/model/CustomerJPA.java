package com.fiap.burger.gateway.customer.model;

import com.fiap.burger.entity.customer.Customer;
import com.fiap.burger.gateway.misc.common.BaseDomainJPA;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@Table(name = "customer")
public class CustomerJPA extends BaseDomainJPA {

    @Column(nullable = false)
    String cpf;

    @Column(nullable = false)
    private String email;

    @Column(nullable = false)
    private String name;

    public CustomerJPA() {

    }

    public CustomerJPA(Long id, String cpf, String email, String name, LocalDateTime createdAt,
                       LocalDateTime modifiedAt,
                       LocalDateTime deletedAt) {
        this.id = id;
        this.cpf = cpf;
        this.email = email;
        this.name = name;
        this.createdAt = createdAt;
        this.modifiedAt = modifiedAt;
        this.deletedAt = deletedAt;
    }

    public String getCpf() {
        return cpf;
    }

    public String getEmail() {
        return email;
    }

    public String getName() {
        return name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Customer customer)) return false;
        return Objects.equals(hashCode(), customer.hashCode());
    }

    @Override
    public int hashCode() {
        return Objects.hash(
            getId(),
            getCpf(),
            getEmail(),
            getName(),
            getCreatedAt(),
            getModifiedAt(),
            getDeletedAt()
        );
    }

    public static CustomerJPA toJPA(Customer customer) {
        if (customer == null) return null;
        return new CustomerJPA(
            customer.getId(),
            customer.getCpf(),
            customer.getEmail(),
            customer.getName(),
            customer.getCreatedAt(),
            customer.getModifiedAt(),
            customer.getDeletedAt()
        );
    }

    public Customer toEntity() {
        return new Customer(id, cpf, email, name, createdAt, modifiedAt, deletedAt);
    }
}

