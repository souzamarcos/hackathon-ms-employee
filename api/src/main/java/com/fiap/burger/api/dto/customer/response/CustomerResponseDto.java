package com.fiap.burger.api.dto.customer.response;

import com.fiap.burger.entity.customer.Customer;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record CustomerResponseDto(

    @NotBlank
    String id,
    @NotBlank
    String cpf,
    @NotBlank
    @Email
    String email,
    @NotBlank
    String name
) {
    public static CustomerResponseDto toResponseDto(Customer customer) {
        return new CustomerResponseDto(
            customer.getId(),
            customer.getCpf(),
            customer.getEmail(),
            customer.getName()
        );
    }
}
