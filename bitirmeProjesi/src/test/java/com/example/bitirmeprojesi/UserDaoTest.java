package com.example.bitirmeprojesi;

import com.example.bitirmeprojesi.dao.UserDao;
import com.example.bitirmeprojesi.dao.UserDaoImpl;
import com.example.bitirmeprojesi.model.UserEntity;
import com.example.bitirmeprojesi.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class UserDaoTest extends BaseIntegrationTest{

    @Mock
    UserDao userDao;

    @Mock
    UserRepository userRepository;

    void setUp(){
        userDao = new UserDaoImpl(userRepository);
    }

    @Test
    public void should_retrieve_user(){

        //mock
        UserEntity mockUserEntity = new UserEntity();
        mockUserEntity.setCreditScore(1000L);
        mockUserEntity.setCreditStatus(true);
        mockUserEntity.setCreditLimit(1000L);
        mockUserEntity.setIdentity("12345678912");
        mockUserEntity.setFirstName("firstName");
        mockUserEntity.setLastName("lastName");
        mockUserEntity.setGsm("5555555555");
        mockUserEntity.setRevenue(1000L);
        /*given(userRepository.save(userEntity)).returns();*/
        when(userDao.retrieveUser(1L)).thenReturn(mockUserEntity);

        //when
        UserEntity userEntity = userDao.retrieveUser(1L);

        //then
        assertThat(userEntity).isNotNull();
        assertThat(userEntity.getCreditStatus()).isEqualTo(true);
        assertThat(userEntity.getIdentity()).isEqualTo("12345678912");
        assertThat(userEntity.getRevenue()).isEqualTo(1000L);
        assertThat(userEntity.getCreditLimit()).isEqualTo(1000L);
        assertThat(userEntity.getFirstName()).isEqualTo("firstName");
        assertThat(userEntity.getLastName()).isEqualTo("lastName");
        assertThat(userEntity.getCreditScore()).isEqualTo(1000L);
        assertThat(userEntity.getGsm()).isEqualTo("5555555555");

    }

    @Test
    void should_retrieve_user_by_identity(){

        //mock
        UserEntity mockUserEntity = new UserEntity();
        mockUserEntity.setCreditScore(2000L);
        mockUserEntity.setCreditStatus(false);
        mockUserEntity.setCreditLimit(2000L);
        mockUserEntity.setIdentity("44444444444");
        mockUserEntity.setFirstName("firstName2");
        mockUserEntity.setLastName("lastName2");
        mockUserEntity.setGsm("4444444444");
        mockUserEntity.setRevenue(2000L);
        /*given(userRepository.save(userEntity)).returns();*/
        when(userDao.retrieveUserByIdentity("44444444444")).thenReturn(mockUserEntity);

        //when
        UserEntity userEntity = userDao.retrieveUserByIdentity("44444444444");

        //then
        assertThat(userEntity).isNotNull();
        assertThat(userEntity.getCreditStatus()).isEqualTo(false);
        assertThat(userEntity.getIdentity()).isEqualTo("44444444444");
        assertThat(userEntity.getRevenue()).isEqualTo(2000L);
        assertThat(userEntity.getCreditLimit()).isEqualTo(2000L);
        assertThat(userEntity.getFirstName()).isEqualTo("firstName2");
        assertThat(userEntity.getLastName()).isEqualTo("lastName2");
        assertThat(userEntity.getCreditScore()).isEqualTo(2000L);
        assertThat(userEntity.getGsm()).isEqualTo("4444444444");
    }

    @Test
    void should_create_user(){

        //mock
        UserEntity mockUserEntity = new UserEntity();
        mockUserEntity.setCreditScore(2000L);
        mockUserEntity.setCreditStatus(false);
        mockUserEntity.setCreditLimit(2000L);
        mockUserEntity.setIdentity("44444444444");
        mockUserEntity.setFirstName("firstName2");
        mockUserEntity.setLastName("lastName2");
        mockUserEntity.setGsm("4444444444");
        mockUserEntity.setRevenue(2000L);
        when(userDao.createUser(mockUserEntity)).thenReturn(true);

        //when
        Boolean creditStatusResult = userDao.createUser(mockUserEntity);

        //then
        assertThat(creditStatusResult).isEqualTo(true);

    }

    @Test
    void check_if_identity_exist(){

        //mock
        when(userRepository.existsByIdentity("11223344556")).thenReturn(true);

        //when
        Boolean ifIdentityExist = userRepository.existsByIdentity("11223344556");

        //then
        assertThat(ifIdentityExist).isEqualTo(true);

    }

}
