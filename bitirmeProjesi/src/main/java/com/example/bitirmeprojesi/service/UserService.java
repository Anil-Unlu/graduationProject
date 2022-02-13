package com.example.bitirmeprojesi.service;

public interface UserService {
    Boolean createUser(User user);
    User retrieveUser(Long id);
    UserCredit retrieveUserByIdentity(String identity);
    Long updateUser(User user, Long id);
    void removeUser(Long id);
    Long calculateCredit();
    Credit calculateCreditStatus(Long creditScore, Long revenue);
}
