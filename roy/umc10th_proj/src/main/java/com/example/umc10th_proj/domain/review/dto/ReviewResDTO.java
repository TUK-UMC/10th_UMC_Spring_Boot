package com.example.umc10th_proj.domain.review.dto;

import lombok.Builder;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

public class ReviewResDTO {

    @Builder
    public record CreateReviewResult(
            Long reviewId,
            LocalDateTime createdAt
    ) {}

    @Builder
    public record MyReviewInfo(
            Long reviewId,
            String storeName,
            String content,
            BigDecimal star,
            LocalDateTime createdAt
    ) {}

    @Builder
    public record MyReviewPreViewList(
            List<MyReviewInfo> reviewList,
            Boolean hasNext,
            Long nextCursorId,
            BigDecimal nextCursorStar
    ) {}
}