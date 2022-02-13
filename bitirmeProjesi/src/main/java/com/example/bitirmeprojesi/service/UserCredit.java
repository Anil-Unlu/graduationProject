package com.example.bitirmeprojesi.service;

import com.example.bitirmeprojesi.model.UserEntity;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class UserCredit {

    private Boolean creditStatus;

    private Long creditLimit;

    public static UserCredit convertFrom(UserEntity userEntity){
        return UserCredit.builder()
                .creditStatus(userEntity.getCreditStatus())
                .creditLimit(userEntity.getCreditLimit())
                .build();
    }

}
