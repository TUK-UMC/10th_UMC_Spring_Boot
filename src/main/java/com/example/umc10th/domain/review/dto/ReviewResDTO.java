package com.example.umc10th.domain.review.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

public class ReviewResDTO {

    // 가게의 리뷰 리스트와 페이징 정보 (오프셋 기반)
    @Builder
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class ReviewPreViewListDTO {
        private List<ReviewPreViewDTO> reviewList;
        private Integer listSize;
        private Integer totalPage;
        private Long totalElements;
        private Boolean isFirst;
        private Boolean isLast;
    }

    // 개별 리뷰 (가게 리뷰 화면용)
    @Builder
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class ReviewPreViewDTO {
        private String nickname;
        private Float score;
        private String body;
        private LocalDate createdAt;
    }

    // 내가 작성한 리뷰 리스트 + 커서 정보 (커서 기반)
    @Builder
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class MyReviewPreViewListDTO {
        private List<MyReviewPreViewDTO> reviewList;
        private Integer listSize;
        private Boolean hasNext;
        private Long nextCursorId;      // ID/별점 순 모두에서 사용 (tie-break용)
        private Float nextCursorScore;  // 별점 순일 때만 채워짐
    }

    // 내가 작성한 리뷰 개별 정보 (사진 제외)
    @Builder
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class MyReviewPreViewDTO {
        private Long reviewId;
        private String storeName;
        private Float score;
        private String body;
        private LocalDate createdAt;
    }
}
