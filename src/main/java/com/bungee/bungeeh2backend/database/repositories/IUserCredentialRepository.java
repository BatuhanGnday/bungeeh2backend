package com.bungee.bungeeh2backend.database.repositories;

import com.bungee.bungeeh2backend.database.models.users.credential.BungeeUserDetails;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IUserCredentialRepository extends JpaRepository<BungeeUserDetails, Integer> {
    BungeeUserDetails findByUsername(String username);

    boolean existsByUsername(String username);
}
