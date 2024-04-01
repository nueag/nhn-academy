package com.nhnacademy.exam.exception;

public class ParameterNotFoundException extends RuntimeException {
    public ParameterNotFoundException(String message) {
        super(message);
    }
}
