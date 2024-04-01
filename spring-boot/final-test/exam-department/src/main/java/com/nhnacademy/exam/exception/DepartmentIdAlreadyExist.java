package com.nhnacademy.exam.exception;

public class DepartmentIdAlreadyExist extends RuntimeException {

    public DepartmentIdAlreadyExist(String message) {
        super(message);
    }
}
