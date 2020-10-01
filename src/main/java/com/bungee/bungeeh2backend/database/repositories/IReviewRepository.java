package com.bungee.bungeeh2backend.database.repositories;

import com.bungee.bungeeh2backend.database.models.restaurant.review.Review;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IReviewRepository extends JpaRepository<Review, Integer> {
}
