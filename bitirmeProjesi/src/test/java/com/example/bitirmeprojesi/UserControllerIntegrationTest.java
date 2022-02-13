package com.example.bitirmeprojesi;

import com.example.bitirmeprojesi.controller.UserCreditResponse;
import com.example.bitirmeprojesi.controller.UserRequest;
import com.example.bitirmeprojesi.controller.UserResponse;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.jdbc.Sql;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class UserControllerIntegrationTest extends BaseIntegrationTest {


    @Test
    @Sql(scripts = "/cleanup.sql", executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
    void should_create_user(){

        //given
        //sqlscript
        UserRequest userRequest = new UserRequest();
        userRequest.setGsm("555555555");
        userRequest.setFirstName("firstName");
        userRequest.setLastName("lastName");
        userRequest.setRevenue(2000L);
        userRequest.setIdentity("12345678912");

        //when
        ResponseEntity<String> response = testRestTemplate.postForEntity("/user", userRequest, String.class);

        //then
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.CREATED);
        assertThat(response.getBody()).isNotNull();

    }

    @Test
    @Sql(scripts = "/update_user.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    @Sql(scripts = "/cleanup.sql", executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
    void should_update_user(){

        //given
        //sqlscript
        UserRequest userRequest = new UserRequest();
        userRequest.setIdentity("12312312312");
        userRequest.setRevenue(5000L);
        userRequest.setLastName("lastName2");
        userRequest.setFirstName("firstName2");
        userRequest.setGsm("5554443322");


        //when
        testRestTemplate.put("/user/2", userRequest);
        ResponseEntity<UserResponse> response = testRestTemplate.getForEntity("/user/2", UserResponse.class);

        //then
        assertThat(response.getBody()).isNotNull();
        assertThat(response.getBody().getRevenue()).isEqualTo(5000);
        assertThat(response.getBody().getGsm()).isEqualTo("5554443322");
        assertThat(response.getBody().getIdentity()).isEqualTo("12312312312");
        assertThat(response.getBody().getFirstName()).isEqualTo("firstName2");
        assertThat(response.getBody().getLastName()).isEqualTo("lastName2");

    }

    @Test
    @Sql(scripts = "/create_user.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    @Sql(scripts = "/cleanup.sql", executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
    void should_retrieve_user_by_id(){

        //given
        //sqlscript

        //when
        ResponseEntity<UserResponse> response = testRestTemplate.getForEntity("/user/1", UserResponse.class);

        //then
        assertThat(response.getBody()).isNotNull();
        assertThat(response.getBody().getFirstName()).isEqualTo("firstName");
        assertThat(response.getBody().getLastName()).isEqualTo("lastName");
        assertThat((response.getBody().getGsm())).isEqualTo("5555555555");
        assertThat(response.getBody().getIdentity()).isEqualTo("12345678912");
        assertThat(response.getBody().getRevenue()).isEqualTo(1000);

    }

    @Test
    @Sql(scripts = "/create_user.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    @Sql(scripts = "/cleanup.sql", executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
    void should_retrieve_credit_result(){

        //given
        //sqlscript
        String identity="12345678912";

        //when
        ResponseEntity<UserCreditResponse> response = testRestTemplate.getForEntity("/users/"+identity , UserCreditResponse.class);

        //then
        assertThat(response.getBody()).isNotNull();
        assertThat(response.getBody().getCreditLimit()).isEqualTo(4000);
        assertThat(response.getBody().getCreditStatus()).isEqualTo(true);

    }



    @Test
    @Sql(scripts = "/create_user.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    void should_remove_user(){

        //given
        //sqlscript

        //when
        testRestTemplate.delete("/user/1");
        ResponseEntity<UserResponse> response = testRestTemplate.getForEntity("/user/1", null);

        //then
        assertThat(response.getBody()).isNull();

    }


}
