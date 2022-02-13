package com.example.bitirmeprojesi.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Table(name = "\"user\"")
@Entity(name = "userEntity")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String identity;

    @Column(nullable = false)
    private String firstName;

    @Column(nullable = false)
    private String lastName;

    @Column(nullable = false)
    private String gsm;

    @Column(nullable = false)
    private Long revenue;

    @Column(nullable = false)
    private Boolean creditStatus;

    @Column(nullable = false)
    private Long creditScore;

    @Column(nullable = false)
    private Long creditLimit;


}
