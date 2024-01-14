package com.fiap.burger.gateway.misc;

import com.fiap.burger.gateway.customer.model.CustomerJPA;

import java.time.LocalDateTime;

public class CustomerJPABuilder {

    private Long id = 1L;

    private String cpf = "68203895077";

    private String email = "email@email.com";

    private String name = "Nome do Cliente";

    private LocalDateTime createdAt = LocalDateTime.now();

    private LocalDateTime modifiedAt = LocalDateTime.now();

    private LocalDateTime deletedAt = null;

    public CustomerJPABuilder withId(Long id) {
        this.id = id;
        return this;
    }

    public CustomerJPABuilder withCpf(String cpf) {
        this.cpf = cpf;
        return this;
    }

    public CustomerJPABuilder withEmail(String email) {
        this.email = email;
        return this;
    }

    public CustomerJPABuilder withName(String name) {
        this.name = name;
        return this;
    }

    public CustomerJPA build() {
        return new CustomerJPA(id, cpf, email, name, createdAt, modifiedAt, deletedAt);
    }

}
