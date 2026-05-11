package com.example.toy.domain.review.dto;

import lombok.Getter;

public class ReviewReqDTO {

    @Getter
    public static class CreateReviewDTO {
        private Float score;
        private String body;
        private Long memberId;
    }
}
