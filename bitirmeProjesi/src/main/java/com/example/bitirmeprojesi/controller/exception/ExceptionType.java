package com.example.bitirmeprojesi.controller.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum ExceptionType {

    USER_DATA_NOT_FOUND(1001, "User bulunamadı."),
    USER_IDENTITY_DATA_NOT_FOUND(1002, "Identity bulunamadı."),
    USER_IDENTITY_ALREADY_EXIST(1003, "Identity başka bir kullanıcıya ait.Yeni bir identity girmelisiniz."),
    GENERIC_EXCEPTION(1, "Bir hata oluştu.");

    private final Integer code;
    private final String message;
}
