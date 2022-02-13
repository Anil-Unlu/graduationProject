package com.example.bitirmeprojesi.dao;

import com.example.bitirmeprojesi.controller.exception.DataNotFoundException;
import com.example.bitirmeprojesi.controller.exception.ExceptionType;
import com.example.bitirmeprojesi.model.UserEntity;
import com.example.bitirmeprojesi.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserDaoImpl implements UserDao{

    private final UserRepository userRepository;

    //userRepository çağırdığımız ve crud işlemlerini yaptığımız son kısımdır.

    @Override
    public Boolean createUser(UserEntity userEntity) {
        Boolean ifExistIdentity = userRepository.existsByIdentity(userEntity.getIdentity());
        if(ifExistIdentity)
            throw new DataNotFoundException(ExceptionType.USER_IDENTITY_ALREADY_EXIST);
        return userRepository.save(userEntity).getCreditStatus();
    }

    @Override
    public Long updateUser(UserEntity userEntity, Long id) {
        Boolean ifExistIdentity = userRepository.existsByIdentity(userEntity.getIdentity());
        if(ifExistIdentity)
            throw new DataNotFoundException(ExceptionType.USER_IDENTITY_ALREADY_EXIST);
       UserEntity userEntityExisted = userRepository.findById(id).orElseThrow(()-> new DataNotFoundException(ExceptionType.USER_DATA_NOT_FOUND));
        userEntityExisted.setIdentity(userEntity.getIdentity());
        userEntityExisted.setFirstName(userEntity.getFirstName());
        userEntityExisted.setLastName(userEntity.getLastName());
        userEntityExisted.setRevenue(userEntity.getRevenue());
        userEntityExisted.setGsm(userEntity.getGsm());
        userEntityExisted.setCreditLimit(userEntityExisted.getCreditLimit());
        userEntityExisted.setCreditScore(userEntityExisted.getCreditScore());
        userEntityExisted.setCreditStatus((userEntityExisted.getCreditStatus()));
        userRepository.save(userEntityExisted);
        return userEntityExisted.getId();
    }

    @Override
    public UserEntity retrieveUser(Long id) {
        Optional<UserEntity> userEntity = userRepository.findById(id);
        return userEntity.get();
    }

    @Override
    public UserEntity retrieveUserByIdentity(String identity) {
        return userRepository.retrieveUserByIdentity(identity);
    }

    @Override
    public void removeUser(Long id) {
        userRepository.deleteById(id);
    }

}
