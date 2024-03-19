package com.fiap.hackathon.usecase.adapter.gateway;

import com.fiap.hackathon.entity.employee.Employee;

public interface EmployeeGateway {
    Employee findById(String id);

    Employee save(Employee employee);
}
