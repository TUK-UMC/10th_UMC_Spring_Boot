package com.example.umc10th_proj.domain.review.converter;

import com.example.umc10th_proj.domain.review.dto.ReviewResDTO;
import com.example.umc10th_proj.domain.review.entity.Review;

public class ReviewConverter {

    public static ReviewResDTO.CreateReviewResult toCreateReviewResult(Review review) {
        return ReviewResDTO.CreateReviewResult.builder()
                .reviewId(review.getId())
                .createdAt(review.getCreatedAt())
                .build();
    }
}