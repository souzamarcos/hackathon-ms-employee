package com.fiap.hackathon.controller.controller;

import com.fiap.hackathon.controller.adapter.api.EmployeeController;
import com.fiap.hackathon.entity.employee.Employee;
import com.fiap.hackathon.usecase.adapter.usecase.EmployeeUseCase;
import com.fiap.hackathon.usecase.misc.exception.EmployeeNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class DefaultEmployeeController implements EmployeeController {
    @Autowired
    private EmployeeUseCase useCase;
    @Override
    public Employee insert(Employee employee) {
        return useCase.insert(employee);
    }

    @Override
    public Employee findById(String id) {
        var persistedEmployee = useCase.findById(id);
        if (persistedEmployee == null) throw new EmployeeNotFoundException(id);
        return persistedEmployee;
    }

    @Override
    public String login(String id, String password) {
        var employeeToken = useCase.login(id, password);
        if (employeeToken == null) throw new EmployeeNotFoundException();
        return employeeToken;
    }
}
