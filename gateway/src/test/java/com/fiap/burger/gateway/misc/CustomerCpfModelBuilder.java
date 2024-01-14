package com.fiap.burger.gateway.misc;

import com.fiap.burger.gateway.customercpf.model.CustomerCpfModel;

public class CustomerCpfModelBuilder {

    private Long id = 1L;

    private String cpf = "68203895077";

    public CustomerCpfModelBuilder withId(Long id) {
        this.id = id;
        return this;
    }

    public CustomerCpfModelBuilder withCpf(String cpf) {
        this.cpf = cpf;
        return this;
    }

    public CustomerCpfModel build() {
        return new CustomerCpfModel(cpf, id);
    }

}
