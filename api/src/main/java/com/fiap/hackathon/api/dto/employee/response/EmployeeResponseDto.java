package com.fiap.hackathon.api.dto.employee.response;

import com.fiap.hackathon.entity.employee.Employee;
import com.fiap.hackathon.entity.employee.EmployeeType;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record EmployeeResponseDto (

    @NotBlank
    String id,
    @NotBlank
    String name,
    @NotBlank
    @Email
    String email,
    @NotBlank
    EmployeeType employeeType
){
    public static EmployeeResponseDto toResponseDto(Employee employee) {
        return new EmployeeResponseDto(
            employee.getId(),
            employee.getName(),
            employee.getEmail(),
            employee.getType()
        );
    }
}
