package com.example.bitirmeprojesi.service;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Credit {

    private Boolean creditStatus;

    private Long creditLimit;

    private final Long creditLimitMultiplier = 4L;

}
