package com.bungee.bungeeh2backend.api.services;

import com.bungee.bungeeh2backend.api.exceptions.NotFoundException;
import com.bungee.bungeeh2backend.database.models.restaurant.Restaurant;
import com.bungee.bungeeh2backend.database.models.restaurant.review.Review;
import com.bungee.bungeeh2backend.database.repositories.IReviewRepository;
import com.bungee.bungeeh2backend.database.repositories.RestaurantRepository;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReviewService {

    //<editor-fold desc="Fields">

    private final IReviewRepository reviewRepository;
    private final RestaurantRepository restaurantRepository;

    //</editor-fold>

    //<editor-fold desc="Constructor">

    public ReviewService(
            IReviewRepository reviewRepository,
            RestaurantRepository restaurantRepository) {
        this.reviewRepository = reviewRepository;
        this.restaurantRepository = restaurantRepository;
    }

    //</editor-fold>

    public Review getReviewById(int id) {
        return reviewRepository.findById(id)
                .orElseThrow(NotFoundException::new);
    }

    public List<Review> getReviewsOfARestaurant(int restaurantId) {
        Restaurant restaurant = restaurantRepository
                .findById(restaurantId)
                .orElseThrow(NotFoundException::new);

        return restaurant.getReviewList();
    }

    public Review createReviewToRestaurant(int restaurantId, Review review) {
        Restaurant restaurant = restaurantRepository
                .findById(restaurantId)
                .orElseThrow(NotFoundException::new);
        // TODO: do necessary checks
        restaurant.getReviewList().add(review);

        return review;
    }


}
