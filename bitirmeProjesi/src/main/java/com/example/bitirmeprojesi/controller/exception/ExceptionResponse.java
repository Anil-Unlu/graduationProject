package com.example.bitirmeprojesi.controller.exception;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ExceptionResponse {

    private Integer code;
    private String message;

    public ExceptionResponse(ExceptionType exceptionType){
        this.code = exceptionType.getCode();
        this.message = exceptionType.getMessage();
    }

}
