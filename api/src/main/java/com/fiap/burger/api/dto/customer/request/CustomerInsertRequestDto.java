package com.fiap.burger.api.dto.customer.request;

import com.fiap.burger.entity.customer.Customer;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record CustomerInsertRequestDto(

    @NotBlank String cpf,
    @NotBlank
    @Email
    String email,

    @NotBlank String name
) {

    public Customer toEntity() {
        return new Customer(cpf, email, name);
    }

    @Override
    public String toString() {
        return "CustomerInsertRequestDto{" +
            "cpf='" + cpf + '\'' +
            ", email='" + email + '\'' +
            ", name='" + name + '\'' +
            '}';
    }
}
