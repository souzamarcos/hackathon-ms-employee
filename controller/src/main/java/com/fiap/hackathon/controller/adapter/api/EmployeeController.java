package com.fiap.hackathon.controller.adapter.api;

import com.fiap.hackathon.entity.employee.Employee;

public interface EmployeeController {
    Employee insert(Employee employee);
    Employee findById(String id);
    String login(String id, String password);
}
