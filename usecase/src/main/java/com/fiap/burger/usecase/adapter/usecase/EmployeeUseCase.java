package com.fiap.burger.usecase.adapter.usecase;

import com.fiap.burger.entity.employee.Employee;

public interface EmployeeUseCase {
    Employee insert(Employee employee);
    Employee findById(String id);
    String login(String id, String password);

}
