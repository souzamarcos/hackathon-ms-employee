package com.fiap.burger.usecase.misc.exception;

public class CustomerNotFoundException extends NotFoundException {

    public CustomerNotFoundException() {
        super("Customer not found");
    }

    public CustomerNotFoundException(String customerId) {
        super("Customer " + customerId + " not found");
    }
}
