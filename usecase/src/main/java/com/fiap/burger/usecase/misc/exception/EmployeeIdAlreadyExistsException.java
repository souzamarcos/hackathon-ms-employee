package com.fiap.burger.usecase.misc.exception;

public class EmployeeIdAlreadyExistsException extends DomainException {

    public EmployeeIdAlreadyExistsException() {
        super("Employee Id already exists");
    }

}
