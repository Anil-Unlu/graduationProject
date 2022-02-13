package com.example.bitirmeprojesi.dao;

import com.example.bitirmeprojesi.model.UserEntity;

public interface UserDao {
    Boolean createUser(UserEntity userEntity);
    Long updateUser(UserEntity userEntity, Long id);
    UserEntity retrieveUser(Long id);
    UserEntity retrieveUserByIdentity(String identity);
    void removeUser(Long id);
}
