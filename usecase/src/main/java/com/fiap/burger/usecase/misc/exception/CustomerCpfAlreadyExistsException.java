package com.fiap.burger.usecase.misc.exception;

public class CustomerCpfAlreadyExistsException extends DomainException {

    public CustomerCpfAlreadyExistsException() {
        super("Customer CPF already exists");
    }

}
