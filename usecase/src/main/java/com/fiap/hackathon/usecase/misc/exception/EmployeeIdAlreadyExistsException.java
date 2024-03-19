package com.fiap.hackathon.usecase.misc.exception;

public class EmployeeIdAlreadyExistsException extends DomainException {

    public EmployeeIdAlreadyExistsException() {
        super("Employee Id already exists");
    }

}
