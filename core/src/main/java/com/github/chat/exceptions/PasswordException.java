package com.github.chat.exceptions;

public class PasswordException extends RuntimeException{

    public PasswordException() {
    }

    public PasswordException(String message) {
        super(message);
    }
}
