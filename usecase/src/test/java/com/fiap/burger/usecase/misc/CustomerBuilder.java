package com.fiap.burger.usecase.misc;

import com.fiap.burger.entity.customer.Customer;

import java.time.LocalDateTime;

public class CustomerBuilder {

    private Long id = 1L;

    private String cpf = "68203895077";

    private String email = "email@email.com";

    private String name = "Nome do Cliente";

    private LocalDateTime createdAt = LocalDateTime.now();

    private LocalDateTime modifiedAt = LocalDateTime.now();

    private LocalDateTime deletedAt = null;

    public CustomerBuilder withId(Long id) {
        this.id = id;
        return this;
    }

    public CustomerBuilder withCpf(String cpf) {
        this.cpf = cpf;
        return this;
    }

    public CustomerBuilder withEmail(String email) {
        this.email = email;
        return this;
    }

    public CustomerBuilder withName(String name) {
        this.name = name;
        return this;
    }

    public Customer build() {
        return new Customer(id, cpf, email, name, createdAt, modifiedAt, deletedAt);
    }

}
