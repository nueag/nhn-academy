package com.nhnacademy.exam.exception;

public class AuthNotFoundException extends RuntimeException {
    public AuthNotFoundException(String message) {
        super(message);
    }
}
