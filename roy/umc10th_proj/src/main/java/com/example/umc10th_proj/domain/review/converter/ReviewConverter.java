package com.example.umc10th_proj.domain.review.converter;

import com.example.umc10th_proj.domain.review.dto.ReviewResDTO;
import com.example.umc10th_proj.domain.review.entity.Review;
import org.springframework.data.domain.Slice;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

public class ReviewConverter {

    public static ReviewResDTO.CreateReviewResult toCreateReviewResult(Review review) {
        return ReviewResDTO.CreateReviewResult.builder()
                .reviewId(review.getId())
                .createdAt(review.getCreatedAt())
                .build();
    }

    public static ReviewResDTO.MyReviewPreViewList toMyReviewPreViewList(Slice<Review> reviewSlice) {
        List<ReviewResDTO.MyReviewInfo> reviewInfoList = reviewSlice.getContent().stream()
                .map(review -> ReviewResDTO.MyReviewInfo.builder()
                        .reviewId(review.getId())
                        .storeName(review.getStore().getName())
                        .content(review.getContent())
                        .star(review.getStar())
                        .createdAt(review.getCreatedAt())
                        .build())
                .collect(Collectors.toList());

        Long nextCursorId = null;
        BigDecimal nextCursorStar = null;

        if (reviewSlice.hasNext() && !reviewInfoList.isEmpty()) {
            ReviewResDTO.MyReviewInfo lastElement = reviewInfoList.get(reviewInfoList.size() - 1);
            nextCursorId = lastElement.reviewId();
            nextCursorStar = lastElement.star();
        }

        return ReviewResDTO.MyReviewPreViewList.builder()
                .reviewList(reviewInfoList)
                .hasNext(reviewSlice.hasNext())
                .nextCursorId(nextCursorId)
                .nextCursorStar(nextCursorStar)
                .build();
    }
}