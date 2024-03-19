package com.fiap.hackathon.api.dto.employee.request;

import com.fiap.hackathon.entity.employee.Employee;
import com.fiap.hackathon.entity.employee.EmployeeType;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record EmployeeInsertRequestDto (
    @NotBlank
    String id,
    @NotBlank
    String name,
    @NotBlank
    @Email
    String email,
    @NotBlank
    EmployeeType employeeType,
    @NotBlank
    String password
) {

    public Employee toEntity() {
        return new Employee(id, name, email, employeeType, password);
    }

}

