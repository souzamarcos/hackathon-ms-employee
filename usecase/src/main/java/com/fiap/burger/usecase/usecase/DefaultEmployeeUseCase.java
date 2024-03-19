package com.fiap.burger.usecase.usecase;

import com.fiap.burger.entity.employee.Employee;
import com.fiap.burger.usecase.adapter.gateway.EmployeeGateway;
import com.fiap.burger.usecase.adapter.usecase.EmployeeUseCase;
import com.fiap.burger.usecase.misc.exception.EmployeeIdAlreadyExistsException;
import com.fiap.burger.usecase.misc.exception.EmployeeNotFoundException;
import com.fiap.burger.usecase.misc.token.TokenJwtUtils;

import static com.fiap.burger.usecase.misc.validation.ValidationUtils.*;

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
        validateNotNull(employee.getEmail(), "email");
        validateNotBlank(employee.getEmail(), "email");
        validateEmailFormat(employee.getEmail());
        validateNotNull(employee.getName(), "name");
        validateNotBlank(employee.getName(), "name");
        validateNotNull(employee.getPassword(), "password");
        validateNotBlank(employee.getPassword(), "password");
    }

    private Boolean comparePasswords(String searchPassword, String storedPassword) {
        return tokenJwtUtils.generatePasswordToken(searchPassword).equals(storedPassword);
    }
}
