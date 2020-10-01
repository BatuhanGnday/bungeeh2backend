package com.bungee.bungeeh2backend.database.repositories;

import com.bungee.bungeeh2backend.database.models.users.credential.UserCredential;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IUserCredentialRepository extends JpaRepository<UserCredential, Integer> {
    UserCredential findByUsername(String username);
}
