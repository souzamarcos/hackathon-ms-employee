package com.fiap.burger.api.dto.employee.response;

import jakarta.validation.constraints.NotBlank;

public record LoginResponseDto(
    @NotBlank
    String employeeToken
){
    public static LoginResponseDto toResponseDto(String token) {
        return new LoginResponseDto(
            token
        );
    }
}
