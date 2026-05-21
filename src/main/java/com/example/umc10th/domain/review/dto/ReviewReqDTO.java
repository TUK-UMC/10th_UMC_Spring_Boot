package com.example.umc10th.domain.review.dto;

import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

public class ReviewReqDTO {

    @Getter
    @NoArgsConstructor(access = AccessLevel.PUBLIC)
    public static class CreateReview {
        @NotBlank(message = "리뷰 내용은 필수입니다.")
        @Size(max = 500, message = "리뷰는 500자 이하여야 합니다.")
        private String body;

        @NotNull(message = "별점은 필수입니다.")
        @DecimalMin(value = "0.0", message = "별점은 0.0 이상이어야 합니다.")
        @DecimalMax(value = "5.0", message = "별점은 5.0 이하여야 합니다.")
        private Float score;
    }

    // 내가 작성한 리뷰 조회 - 사용자 ID 는 Request Body 로 받음
    public record GetMyReviews(
            @NotNull(message = "회원 ID는 필수입니다.")
            @Positive(message = "회원 ID는 0보다 커야 합니다.")
            Long memberId
    ){}
}
