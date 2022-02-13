package com.example.bitirmeprojesi.service;
import com.example.bitirmeprojesi.model.UserEntity;
import lombok.*;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class User {

    private String lastName;
    private String firstName;
    private String identity;
    private String gsm;
    private Long revenue;

    public UserEntity convertFrom(){
        UserEntity userEntity = new UserEntity();
        userEntity.setFirstName(firstName);
        userEntity.setGsm(gsm);
        userEntity.setRevenue(revenue);
        userEntity.setIdentity(identity);
        userEntity.setLastName(lastName);
        return userEntity;
    }

    public static User convertFromEntity(UserEntity userEntity){
        return User.builder()
                .revenue(userEntity.getRevenue())
                .firstName(userEntity.getFirstName())
                .lastName(userEntity.getLastName())
                .gsm(userEntity.getGsm())
                .identity(userEntity.getIdentity())
                .build();
    }

}
