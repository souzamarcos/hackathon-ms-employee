package com.fiap.burger.usecase.usecase;

import com.fiap.burger.entity.employee.Employee;
import com.fiap.burger.usecase.adapter.gateway.EmployeeGateway;
import com.fiap.burger.usecase.adapter.usecase.EmployeeUseCase;
import com.fiap.burger.usecase.misc.exception.EmployeeIdAlreadyExistsException;
import com.fiap.burger.usecase.misc.exception.EmployeeNotFoundException;

import static com.fiap.burger.usecase.misc.validation.ValidationUtils.*;

public class DefaultEmployeeUseCase implements EmployeeUseCase {

    private final EmployeeGateway gateway;

    public DefaultEmployeeUseCase(EmployeeGateway gateway) {
        this.gateway = gateway;
    }

    @Override
    public Employee insert(Employee employee) {
        var persistedEmployee = gateway.findById(employee.getId());
        if(persistedEmployee != null) {
            throw new EmployeeIdAlreadyExistsException();
        }
        validateEmployee(employee);
        return gateway.save(employee);
    }

    @Override
    public Employee findById(String id) {
        return gateway.findById(id);
    }

    @Override
    public String login(String id, String password) {
        var persistedEmployee = gateway.findById(id);
        if(persistedEmployee == null || !comparePasswords(password, persistedEmployee.getPassword())) {
            throw new EmployeeNotFoundException();
        }
        return "gerar-token";
    }

    private void validateEmployee(Employee employee) {
        validateNotNull(employee.getEmail(), "email");
        validateNotBlank(employee.getEmail(), "email");
        validateEmailFormat(employee.getEmail());
        validateNotNull(employee.getName(), "name");
        validateNotBlank(employee.getName(), "name");
        validateNotNull(employee.getPassword(), "password");
        validateNotBlank(employee.getPassword(), "password");
    }

    private Boolean comparePasswords(String searchPassword, String storedPassword) {
        return searchPassword.equals(storedPassword);
    }
}
