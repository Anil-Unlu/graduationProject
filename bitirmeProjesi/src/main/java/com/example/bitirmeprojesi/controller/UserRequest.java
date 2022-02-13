package com.example.bitirmeprojesi.controller;

import com.example.bitirmeprojesi.service.User;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@Setter
public class UserRequest {

    @NotBlank
    private String firstName;

    @NotBlank
    private String lastName;

    @NotBlank
    private String identity;

    @NotNull
    private Long revenue;

    @NotBlank
    private String gsm;

    public User convertFrom( ){
        return User.builder()
                .firstName(firstName)
                .gsm(gsm)
                .lastName(lastName)
                .identity(identity)
                .revenue(revenue)
                .build();
    }

}
