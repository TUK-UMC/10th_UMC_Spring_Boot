package com.example.umc10th2.domain.review.dto;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

public class ReviewResDTO {

    @Getter
    @Builder
    public static class CreateRes {
        private Long reviewId;
        private Long storeId;
        private Float rating;
        private String content;
        private LocalDateTime createdAt;
    }
}
