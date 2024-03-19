package com.fiap.hackathon.usecase.misc.exception;

public class InvalidEmailFormatException extends DomainException {

    public InvalidEmailFormatException() {
        super("Invalid email format.");
    }

}
