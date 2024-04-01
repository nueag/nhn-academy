package com.nhnacademy.exam.controller;

import com.nhnacademy.exam.exception.AuthNotFoundException;
import com.nhnacademy.exam.exception.DepartmentIdAlreadyExist;
import com.nhnacademy.exam.exception.DepartmentNotExistException;
import com.nhnacademy.exam.exception.MediaNotFountException;
import com.nhnacademy.exam.exception.ParameterNotFoundException;
import com.nhnacademy.exam.responseDto.ErrorResponse;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ControllerAdvice {
    private Timestamp timestamp;
    private final SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");

    @ExceptionHandler({DepartmentIdAlreadyExist.class, ParameterNotFoundException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public @ResponseBody ErrorResponse handleValidationException(Exception exception) {
        timestamp = new Timestamp(System.currentTimeMillis());
        return new ErrorResponse(exception.getMessage(), "400", formatter.format(timestamp));
    }

    @ExceptionHandler(DepartmentNotExistException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public @ResponseBody ErrorResponse handleRuntimeException(DepartmentNotExistException exception) {
        return new ErrorResponse("department not found : " + exception.getMessage(), "404",
                formatter.format(timestamp));
    }

    @ExceptionHandler(AuthNotFoundException.class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public @ResponseBody ErrorResponse handleAuthException(AuthNotFoundException exception) {
        return new ErrorResponse(exception.getMessage(), "401", formatter.format(timestamp));
    }

    @ExceptionHandler(MediaNotFountException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public @ResponseBody ErrorResponse handleMissMatchContentType(Exception exception) {
        return new ErrorResponse(exception.getMessage(), "400", formatter.format(timestamp));
    }

}

