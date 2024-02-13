package com.example.demo.services.intr;

import com.example.demo.entities.Review;

import java.util.List;
import java.util.UUID;

public interface ReviewServiceInterface {
    Review saveReview(Review review);

    Review getReviewById(UUID id);

    List<Review> getAllReviews();

    void deleteReview(UUID id);

    Review editReview(Review review);
}
