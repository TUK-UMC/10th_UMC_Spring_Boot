package com.example.umc10th2.domain.review.converter;

import com.example.umc10th2.domain.review.dto.ReviewResDTO;
import com.example.umc10th2.domain.review.entity.Review;

import java.util.List;
import java.util.stream.Collectors;

public class ReviewConverter {

    public static ReviewResDTO.ReviewResponse toReviewResponse(Review review) {
        return ReviewResDTO.ReviewResponse.builder()
                .reviewId(review.getId())
                .storeName(review.getStore().getName())
                .body(review.getBody())
                .score(review.getScore())
                .createdAt(review.getCreatedAt())
                .build();
    }

    public static ReviewResDTO.MyReviewCursorResponse toCursorResponse(
            List<Review> reviews, int size, String sortBy) {

        boolean hasNext = reviews.size() > size;
        // size+1개 조회 후 hasNext 판단, 실제 응답은 size개만
        List<Review> result = hasNext ? reviews.subList(0, size) : reviews;

        Long nextCursorId = result.isEmpty() ? null : result.get(result.size() - 1).getId();
        Float nextCursorScore = result.isEmpty() ? null : result.get(result.size() - 1).getScore();

        return ReviewResDTO.MyReviewCursorResponse.builder()
                .reviews(result.stream()
                        .map(ReviewConverter::toReviewResponse)
                        .collect(Collectors.toList()))
                .nextCursorId(nextCursorId)
                .nextCursorScore("score".equals(sortBy) ? nextCursorScore : null)
                .hasNext(hasNext)
                .build();
    }
}
