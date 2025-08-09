package com.logship.user.serivce.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Entity
@Table(name = "users")
@Getter
@Setter
public class User {

    @Id
    @GeneratedValue
    private UUID id;
    @Column(unique = true)
    private String username;
    private String employeeName;
    private String password;
    @Enumerated(EnumType.STRING)
    private Role role;
}
