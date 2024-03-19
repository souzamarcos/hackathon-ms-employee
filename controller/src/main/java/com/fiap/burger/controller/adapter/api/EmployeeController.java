package com.fiap.burger.controller.adapter.api;

import com.fiap.burger.entity.employee.Employee;

public interface EmployeeController {
    Employee insert(Employee employee);
    Employee findById(String id);
    String login(String id, String password);
}
