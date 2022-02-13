package com.example.bitirmeprojesi.controller.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class DataExceptionHandler {
    //Rest için kullanıcıya hatayı gösteren classdır.

    @ExceptionHandler(DataNotFoundException.class)
    @ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
    public ExceptionResponse handleDataNotFoundException(DataNotFoundException e){
        return new ExceptionResponse(e.getExceptionType());
    }

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ExceptionResponse handleException(Exception e){
        return new ExceptionResponse(ExceptionType.GENERIC_EXCEPTION);
    }

}
