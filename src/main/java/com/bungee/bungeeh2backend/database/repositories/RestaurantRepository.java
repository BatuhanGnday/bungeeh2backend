package com.bungee.bungeeh2backend.database.repositories;

import com.bungee.bungeeh2backend.database.models.restaurant.Restaurant;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RestaurantRepository extends JpaRepository<Restaurant, Integer> {
}
