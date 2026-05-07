package com.example.umc10th_proj.domain.review.dto;

import lombok.Builder;
import java.time.LocalDateTime;

public class ReviewResDTO {

    @Builder
    public record CreateReviewResult(
            Long reviewId,
            LocalDateTime createdAt
    ) {}
}