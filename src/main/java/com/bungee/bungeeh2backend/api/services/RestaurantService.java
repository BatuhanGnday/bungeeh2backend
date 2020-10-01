package com.bungee.bungeeh2backend.api.services;

import com.bungee.bungeeh2backend.api.exceptions.NotFoundException;
import com.bungee.bungeeh2backend.database.models.restaurant.Restaurant;
import com.bungee.bungeeh2backend.database.repositories.RestaurantRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RestaurantService {

    //<editor-fold desc="Fields">

    private final RestaurantRepository repository;

    //</editor-fold>

    //<editor-fold desc="Constructor">

    public RestaurantService(RestaurantRepository repository) {
        this.repository = repository;
    }

    //</editor-fold>

    //<editor-fold desc="Functions">

    public Restaurant addRestaurant(Restaurant restaurant) {
        repository.save(restaurant);
        return restaurant;
    }

    public List<Restaurant> getRestaurants() {
        return repository.findAll();
    }

    public Restaurant findById(int id) {
        return repository.findById(id).orElseThrow(NotFoundException::new);
    }

    //</editor-fold>

}
