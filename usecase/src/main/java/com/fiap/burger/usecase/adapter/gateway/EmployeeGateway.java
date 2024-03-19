package com.fiap.burger.usecase.adapter.gateway;

import com.fiap.burger.entity.employee.Employee;

public interface EmployeeGateway {
    Employee findById(String id);

    Employee save(Employee employee);
}
