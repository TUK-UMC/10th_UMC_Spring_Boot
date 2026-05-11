package com.example.toy.domain.review.converter;

import com.example.toy.domain.member.entity.Member;
import com.example.toy.domain.mission.entity.Store;
import com.example.toy.domain.review.dto.ReviewReqDTO;
import com.example.toy.domain.review.dto.ReviewResDTO;
import com.example.toy.domain.review.entity.Review;

public class ReviewConverter {

    public static Review toReview(ReviewReqDTO.CreateReviewDTO request, Member member, Store store) {
        return Review.builder()
                .score(request.getScore())
                .body(request.getBody())
                .member(member)
                .store(store)
                .build();
    }

    public static ReviewResDTO.CreateReviewResultDTO toCreateReviewResultDTO(Review review) {
        return ReviewResDTO.CreateReviewResultDTO.builder()
                .reviewId(review.getId())
                .createdAt(review.getCreatedAt())
                .build();
    }
}
