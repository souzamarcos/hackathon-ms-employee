package com.fiap.hackathon.usecase.adapter.usecase;

import com.fiap.hackathon.entity.employee.Employee;

public interface EmployeeUseCase {
    Employee insert(Employee employee);
    Employee findById(String id);
    String login(String id, String password);

}
