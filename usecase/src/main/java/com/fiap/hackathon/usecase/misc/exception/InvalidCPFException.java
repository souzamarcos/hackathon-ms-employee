package com.fiap.hackathon.usecase.misc.exception;

public class InvalidCPFException extends DomainException {

    public InvalidCPFException() {
        super("Invalid CPF.");
    }

}

