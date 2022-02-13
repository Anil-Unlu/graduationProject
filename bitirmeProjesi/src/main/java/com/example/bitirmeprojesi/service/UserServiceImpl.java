package com.example.bitirmeprojesi.service;

import com.example.bitirmeprojesi.controller.exception.DataNotFoundException;
import com.example.bitirmeprojesi.controller.exception.ExceptionType;
import com.example.bitirmeprojesi.dao.UserDao;
import com.example.bitirmeprojesi.model.UserEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import java.time.Duration;
import java.util.Optional;
import java.util.Random;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService{

    //creditsScore hesaplama bu sayfada yapılıyor.

    private final UserDao userDao;

    private final RedisTemplate<String, User> userRedisTemplate;

    //calculateCreditStatus fonksiyonundan dönen Credit classı bilgileri userEntitye eşitleniyor.
    @Override
    public Boolean createUser(User user) {
        UserEntity userEntity = user.convertFrom();
        userEntity.setCreditScore(calculateCredit());
        Credit credit = calculateCreditStatus(userEntity.getCreditScore(), userEntity.getRevenue());
        userEntity.setCreditStatus(credit.getCreditStatus());
        userEntity.setCreditLimit(credit.getCreditLimit());
        return userDao.createUser(userEntity);
    }

    //retrieveUser da redis kullanılıyor.Cacheden veri burdan alınır.
    @Override
    public User retrieveUser(Long id) {

        User user = userRedisTemplate.opsForValue().get("patika:user:" + id);

        if(user == null){
            UserEntity userEntity = null;
            try{
                userEntity = userDao.retrieveUser(id);
            }
            catch(Exception e){
                if(userEntity == null)
                    throw new DataNotFoundException(ExceptionType.USER_DATA_NOT_FOUND);
                else
                    throw new DataNotFoundException(ExceptionType.GENERIC_EXCEPTION);
            }
            user = User.convertFromEntity(userEntity);
            userRedisTemplate.opsForValue().set("patika:user:" + id, user, Duration.ofSeconds(300));
        }

        return user;
    }

    @Override
    public UserCredit retrieveUserByIdentity(String identity) {
        UserEntity userEntity = null;
        try{
            userEntity = userDao.retrieveUserByIdentity(identity);
        }
        catch(Exception e){
            if(userEntity == null)
                throw new DataNotFoundException(ExceptionType.USER_IDENTITY_DATA_NOT_FOUND);
            else
                throw new DataNotFoundException(ExceptionType.GENERIC_EXCEPTION);
        }
        return UserCredit.convertFrom(userEntity);
    }

    //cachei de update yapıyoruz.
    @Override
    public Long updateUser(User user, Long id) {
        UserEntity userEntity = user.convertFrom();
        userRedisTemplate.opsForValue().set("patika:user:" + id, user, Duration.ofSeconds(300));
        return userDao.updateUser(userEntity, id);
    }

    @Override
    public void removeUser(Long id) {
        UserEntity userEntity= null;
        try{
        userEntity = userDao.retrieveUser(id);
        userDao.removeUser(id);
        }
        catch(Exception e){
            if(userEntity == null)
                throw new DataNotFoundException(ExceptionType.USER_DATA_NOT_FOUND);
            else
                throw new DataNotFoundException(ExceptionType.GENERIC_EXCEPTION);
        }
    }

    //0 ile 2000 arasında random creditscoru hesaplayan fonksiyondur.
    @Override
    public Long calculateCredit() {
        Random randomNumber = new Random();
        return randomNumber.nextLong(2001);
    }

    //Creditstatusu istenen creditscore ve maaşa özelliklerine göre hesaplayan fonksiyondur.
    @Override
    public Credit calculateCreditStatus(Long creditScore, Long revenue) {
        Credit credit = new Credit();

        if(creditScore < 500){
            credit.setCreditStatus(false);
            credit.setCreditLimit(0L);
        }
        else if(creditScore >= 500 && creditScore < 1000 && revenue < 5000){
            credit.setCreditStatus(true);
            credit.setCreditLimit(10000L);
        }
        else if(creditScore >= 500 && creditScore < 1000 && revenue >= 5000){
            credit.setCreditStatus(true);
            credit.setCreditLimit(20000L);
        }
        else if(creditScore >= 1000){
            credit.setCreditStatus(true);
            credit.setCreditLimit(revenue*credit.getCreditLimitMultiplier());
        }

        return credit;
    }
}
