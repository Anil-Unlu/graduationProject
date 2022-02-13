package com.example.bitirmeprojesi.controller;

import com.example.bitirmeprojesi.service.User;
import com.example.bitirmeprojesi.service.UserCredit;
import com.example.bitirmeprojesi.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
public class UserController {

    //user için crud işlemlerinin yapıldığı rest end pointlerinin sayfasıdır.

    private final UserService userService;

    @PostMapping("/user")
    @ResponseStatus(HttpStatus.CREATED)
    public String createUser(@RequestBody @Valid UserRequest userRequest){
        User user = userRequest.convertFrom();
        Boolean result = userService.createUser(user);
        return (result==true) ? "ONAY" : "RED";
    }

    @GetMapping("/user/{id}")
    @ResponseStatus(HttpStatus.OK)
    public UserResponse retrieveUser(@PathVariable Long id){
        User user = userService.retrieveUser(id);
        return UserResponse.convertToResponse(user);
    }

    @PutMapping("/user/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Long updateUser(@RequestBody @Valid UserRequest userRequest, @PathVariable Long id){
        User user = userRequest.convertFrom();
        return userService.updateUser(user, id);
    }

    @GetMapping("/users/{identity}")
    @ResponseStatus(HttpStatus.OK)
    public UserCreditResponse retrieveUserByIdentity(@PathVariable String identity){
        UserCredit userCredit = userService.retrieveUserByIdentity(identity);
        return UserCreditResponse.convertFromUserCredit(userCredit);
    }

    @DeleteMapping("/user/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void removeUser(@PathVariable Long id){
        userService.removeUser(id);
    }

}
