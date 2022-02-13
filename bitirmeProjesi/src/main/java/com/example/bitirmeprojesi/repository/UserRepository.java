package com.example.bitirmeprojesi.repository;

import com.example.bitirmeprojesi.model.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface UserRepository extends JpaRepository<UserEntity, Long> {

    @Query(value = "Select r from userEntity as r where r.identity = :identity")
    UserEntity retrieveUserByIdentity(@Param("identity")String identity);

    Boolean existsByIdentity(String identity);
}
