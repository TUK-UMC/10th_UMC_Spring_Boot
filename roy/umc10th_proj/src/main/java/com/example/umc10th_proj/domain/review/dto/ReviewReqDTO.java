// src/main/java/com/example/umc10th_proj/domain/review/dto/ReviewReqDTO.java
package com.example.umc10th_proj.domain.review.dto;

import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

public class ReviewReqDTO {

    @Getter
    @Setter
    public static class CreateReview {
        @NotNull(message = "별점은 필수 입력 항목입니다.")
        @DecimalMin(value = "0.0", message = "별점은 0 이상이어야 합니다.")
        @DecimalMax(value = "5.0", message = "별점은 5.0 이하이어야 합니다.")
        private Double star;

        @NotBlank(message = "리뷰 내용은 필수 입력 항목입니다.")
        private String content;
    }
}