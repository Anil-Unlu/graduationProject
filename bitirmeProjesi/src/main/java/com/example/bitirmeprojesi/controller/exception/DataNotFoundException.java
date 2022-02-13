package com.example.bitirmeprojesi.controller.exception;

import lombok.Getter;

@Getter
public class DataNotFoundException extends RuntimeException{

    private final ExceptionType exceptionType;

    public DataNotFoundException(ExceptionType exceptionType){
        super(exceptionType.getMessage());
        this.exceptionType = exceptionType;
    }

}
