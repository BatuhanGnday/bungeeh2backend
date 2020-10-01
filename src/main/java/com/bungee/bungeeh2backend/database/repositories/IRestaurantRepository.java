package com.bungee.bungeeh2backend.database.repositories;

import com.bungee.bungeeh2backend.database.models.restaurant.Restaurant;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IRestaurantRepository extends JpaRepository<Restaurant, Integer> {
}
