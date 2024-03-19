package com.fiap.hackathon.usecase.misc.exception;

public class EmployeeNotFoundException extends NotFoundException {

    public EmployeeNotFoundException() {
        super("Employee not found");
    }

    public EmployeeNotFoundException(String id) {
        super("Employee " + id + " not found");
    }
}
