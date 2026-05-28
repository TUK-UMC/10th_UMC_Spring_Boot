package com.example.umc10th2.domain.review.dto;

import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

public class ReviewReqDTO {

    // ✅ 리뷰 작성 요청 (검증 어노테이션 포함)
    @Getter
    @NoArgsConstructor
    public static class CreateReviewRequest {

        @NotNull(message = "회원 ID는 필수입니다.")
        private Long memberId;

        @NotNull(message = "가게 ID는 필수입니다.")
        private Long storeId;

        @NotBlank(message = "리뷰 내용은 필수입니다.")
        @Size(max = 200, message = "리뷰 내용은 200자 이하여야 합니다.")
        private String body;

        @NotNull(message = "별점은 필수입니다.")
        @Min(value = 1, message = "별점은 최소 1점입니다.")
        @Max(value = 5, message = "별점은 최대 5점입니다.")
        private Float score;
    }

    // ✅ 내 리뷰 조회 요청 (커서 기반)
    @Getter
    @NoArgsConstructor
    public static class MyReviewRequest {

        @NotNull(message = "회원 ID는 필수입니다.")
        private Long memberId;

        // 정렬 기준: "id" or "score"
        @NotBlank(message = "정렬 기준은 필수입니다.")
        private String sortBy = "id";

        // 커서값 (첫 조회 시 0 / null)
        private Long lastId = 0L;
        private Float lastScore = Float.MAX_VALUE;

        @Min(value = 1, message = "조회 개수는 1 이상이어야 합니다.")
        @Max(value = 50, message = "조회 개수는 50 이하여야 합니다.")
        private int size = 10;
    }
}
