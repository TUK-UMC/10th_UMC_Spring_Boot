package com.example.umc10th2.domain.review.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.List;

public class ReviewResDTO {

    @Getter
    @Builder
    @AllArgsConstructor
    public static class ReviewResponse {
        private Long reviewId;
        private String storeName;
        private String body;
        private Float score;
        private LocalDateTime createdAt;
    }

    // ✅ 커서 기반 페이지네이션 응답
    @Getter
    @Builder
    @AllArgsConstructor
    public static class MyReviewCursorResponse {
        private List<ReviewResponse> reviews;
        private Long nextCursorId;       // 다음 요청 시 lastId로 사용
        private Float nextCursorScore;   // 다음 요청 시 lastScore로 사용 (별점 정렬 시)
        private boolean hasNext;         // 다음 페이지 존재 여부
    }
}
