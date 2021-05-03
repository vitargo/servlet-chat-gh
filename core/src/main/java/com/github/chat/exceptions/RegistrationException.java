package com.github.chat.exceptions;

public class RegistrationException extends RuntimeException {

    public RegistrationException() {
    }


    public RegistrationException(String message) {
        super(message);
    }
}
