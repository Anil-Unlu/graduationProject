package com.example.bitirmeprojesi.controller;

import com.example.bitirmeprojesi.service.User;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class UserResponse {

    private String firstName;
    private String lastName;
    private String gsm;
    private String identity;
    private Long revenue;

    public static UserResponse convertToResponse(User user){
        return UserResponse.builder()
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .revenue(user.getRevenue())
                .gsm(user.getGsm())
                .identity(user.getIdentity())
                .build();
    }

}
