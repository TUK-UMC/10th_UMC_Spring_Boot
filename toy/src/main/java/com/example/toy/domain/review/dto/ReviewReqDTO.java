package com.example.toy.domain.review.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;

public class ReviewReqDTO {

    @Getter
    public static class CreateReviewDTO {

        @NotNull(message = "점수는 필수입니다.")
        @Min(value = 1, message = "점수는 1점 이상이어야 합니다.")
        @Max(value = 5, message = "점수는 5점 이하여야 합니다.")
        private Float score;

        @NotBlank(message = "리뷰 내용은 빈칸일 수 없습니다.")
        private String body;

        @NotNull(message = "memberId는 필수입니다.")
        private Long memberId;
    }
}
