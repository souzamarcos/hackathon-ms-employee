package com.fiap.hackathon.usecase.usecase;

import com.fiap.hackathon.entity.employee.Employee;
import com.fiap.hackathon.usecase.adapter.gateway.EmployeeGateway;
import com.fiap.hackathon.usecase.adapter.usecase.EmployeeUseCase;
import com.fiap.hackathon.usecase.misc.exception.EmployeeIdAlreadyExistsException;
import com.fiap.hackathon.usecase.misc.exception.EmployeeNotFoundException;
import com.fiap.hackathon.usecase.misc.token.TokenJwtUtils;
import com.fiap.hackathon.usecase.misc.validation.ValidationUtils;

public class DefaultEmployeeUseCase implements EmployeeUseCase {

    private final EmployeeGateway gateway;
    private final TokenJwtUtils tokenJwtUtils;

    public DefaultEmployeeUseCase(EmployeeGateway gateway,
                                  TokenJwtUtils tokenJwtUtils) {
        this.gateway = gateway;
        this.tokenJwtUtils = tokenJwtUtils;
    }

    @Override
    public Employee insert(Employee employee) {
        var persistedEmployee = gateway.findById(employee.getId());
        if(persistedEmployee != null) {
            throw new EmployeeIdAlreadyExistsException();
        }
        validateEmployee(employee);
        employee.setPassword(tokenJwtUtils.generatePasswordToken(employee.getPassword()));
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
        return tokenJwtUtils.generateEmployeeToken(persistedEmployee);
    }

    private void validateEmployee(Employee employee) {
        ValidationUtils.validateNotNull(employee.getEmail(), "email");
        ValidationUtils.validateNotBlank(employee.getEmail(), "email");
        ValidationUtils.validateEmailFormat(employee.getEmail());
        ValidationUtils.validateNotNull(employee.getName(), "name");
        ValidationUtils.validateNotBlank(employee.getName(), "name");
        ValidationUtils.validateNotNull(employee.getPassword(), "password");
        ValidationUtils.validateNotBlank(employee.getPassword(), "password");
    }

    private Boolean comparePasswords(String searchPassword, String storedPassword) {
        return tokenJwtUtils.generatePasswordToken(searchPassword).equals(storedPassword);
    }
}
