package com.fiap.burger.application.utils;

import com.fiap.burger.api.dto.employee.request.EmployeeInsertRequestDto;
import com.fiap.burger.entity.employee.EmployeeType;

public class EmployeeHelper {
    public static EmployeeInsertRequestDto createEmployeeRequest() {
        return new EmployeeInsertRequestDto("ABC001", "Nome", "email@email.com", EmployeeType.USER, "password");
    }
}
