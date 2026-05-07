package com.example.umc10th2.domain.review.converter;

import com.example.umc10th2.domain.member.entity.Member;
import com.example.umc10th2.domain.mission.entity.Store;
import com.example.umc10th2.domain.review.dto.ReviewReqDTO;
import com.example.umc10th2.domain.review.dto.ReviewResDTO;
import com.example.umc10th2.domain.review.entity.Review;

public class ReviewConverter {

    public static Review toReview(ReviewReqDTO req, Member member, Store store) {
        return Review.builder()
                .member(member)
                .store(store)
                .rating(req.getRating())
                .content(req.getContent())
                .build();
    }

    public static ReviewResDTO.CreateRes toCreateRes(Review review) {
        return ReviewResDTO.CreateRes.builder()
                .reviewId(review.getId())
                .storeId(review.getStore().getId())
                .rating(review.getRating())
                .content(review.getContent())
                .createdAt(review.getCreatedAt())
                .build();
    }
}
