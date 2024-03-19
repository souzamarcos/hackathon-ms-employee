package com.fiap.hackathon.application.utils;

import com.fiap.hackathon.api.dto.employee.request.EmployeeInsertRequestDto;
import com.fiap.hackathon.entity.employee.EmployeeType;

public class EmployeeHelper {
    public static EmployeeInsertRequestDto createEmployeeRequest() {
        return new EmployeeInsertRequestDto("ABC001", "Nome", "email@email.com", EmployeeType.USER, "password");
    }
}
