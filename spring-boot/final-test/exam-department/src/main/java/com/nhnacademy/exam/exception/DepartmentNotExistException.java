package com.nhnacademy.exam.exception;

public class DepartmentNotExistException extends RuntimeException {
    public DepartmentNotExistException(String message) {
        super(message);
    }
}
