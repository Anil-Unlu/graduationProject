package com.example.bitirmeprojesi.controller;

import com.example.bitirmeprojesi.service.UserCredit;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class UserCreditResponse {

    private Boolean creditStatus;
    private Long creditLimit;

    public static UserCreditResponse convertFromUserCredit(UserCredit userCredit){
        return UserCreditResponse.builder()
                .creditLimit(userCredit.getCreditLimit())
                .creditStatus(userCredit.getCreditStatus())
                .build();
    }

}
