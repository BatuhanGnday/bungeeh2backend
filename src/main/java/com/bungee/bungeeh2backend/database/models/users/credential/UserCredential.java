package com.bungee.bungeeh2backend.database.models.users.credential;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class UserCredential {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false, unique = true)
    private String username;

    private String password;
}
