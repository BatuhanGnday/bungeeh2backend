package com.bungee.bungeeh2backend.database.repositories;

import com.bungee.bungeeh2backend.database.models.UserProfile;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IUserProfileRepository extends JpaRepository<UserProfile, Integer> {
}
