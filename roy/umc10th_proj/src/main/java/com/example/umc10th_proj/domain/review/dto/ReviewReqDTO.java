package com.example.umc10th_proj.domain.review.dto;

import lombok.Getter;
import lombok.Setter;

public class ReviewReqDTO {

    @Getter
    @Setter
    public static class CreateReview {
        private Double star;
        private String content;
    }
}