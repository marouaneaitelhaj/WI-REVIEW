package com.example.demo.services.impl;

import com.example.demo.entities.Review;
import com.example.demo.repositories.ReviewRepository;
import com.example.demo.services.intr.ReviewServiceInterface;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
@Service
@AllArgsConstructor
public class ReviewService  implements ReviewServiceInterface {

    private  final ReviewRepository reviewRepository;
    @Override
    public Review saveReview(Review review) {
        return reviewRepository.save(review);
    }

    @Override
    public Review getReviewById(UUID id) {
        return null;
    }

    @Override
    public List<Review> getAllReviews() {
        return null;
    }

    @Override
    public void deleteReview(UUID id) {

    }

    @Override
    public Review editReview(Review review) {
        return null;
    }
}
