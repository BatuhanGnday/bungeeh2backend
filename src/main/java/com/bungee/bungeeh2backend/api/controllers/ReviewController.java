package com.bungee.bungeeh2backend.api.controllers;

import com.bungee.bungeeh2backend.api.services.ReviewService;
import com.bungee.bungeeh2backend.database.models.restaurant.review.Review;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/reviews")
public class ReviewController {

    private final ReviewService reviewService;

    public ReviewController(ReviewService reviewService) {
        this.reviewService = reviewService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Review> getReviewById(@PathVariable("id") int id) {
        return ResponseEntity.ok(reviewService.getReviewById(id));
    }
}
