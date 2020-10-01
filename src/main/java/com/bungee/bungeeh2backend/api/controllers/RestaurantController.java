package com.bungee.bungeeh2backend.api.controllers;

import com.bungee.bungeeh2backend.api.services.RestaurantService;
import com.bungee.bungeeh2backend.api.services.ReviewService;
import com.bungee.bungeeh2backend.database.models.restaurant.Restaurant;
import com.bungee.bungeeh2backend.database.models.restaurant.review.Review;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/restaurants")
public class RestaurantController {

    private final RestaurantService restaurantService;
    private final ReviewService reviewService;

    public RestaurantController(RestaurantService restaurantService,
                                ReviewService reviewService) {
        this.restaurantService = restaurantService;
        this.reviewService = reviewService;
    }

    @PostMapping
    public ResponseEntity<Restaurant> saveRestaurant(@RequestBody Restaurant restaurant) {
        return ResponseEntity.ok(restaurantService.addRestaurant(restaurant));
    }

    @GetMapping
    public ResponseEntity<List<Restaurant>> findAllRestaurants() {
        return ResponseEntity.ok(restaurantService.getRestaurants());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Restaurant> findById(@PathVariable int id) {
        return ResponseEntity.ok(restaurantService.findById(id));
    }

    @GetMapping("/{id}/reviews")
    public ResponseEntity<List<Review>> findReviewsByRestaurantId(@PathVariable("id") int id) {
        return ResponseEntity.ok(reviewService.getReviewsOfARestaurant(id));
    }

    @PostMapping("/{id}/reviews")
    public ResponseEntity<Review>
    createReviewToRestaurant(@PathVariable("id") int id,
                             @RequestBody Review review) {
        return ResponseEntity.ok(reviewService.createReviewToRestaurant(id, review));
    }


}
