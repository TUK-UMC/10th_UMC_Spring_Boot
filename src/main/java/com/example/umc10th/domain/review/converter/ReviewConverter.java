package com.example.umc10th.domain.review.converter;

import com.example.umc10th.domain.review.dto.ReviewResDTO;
import com.example.umc10th.domain.review.entity.Review;
import com.example.umc10th.domain.review.enums.ReviewSortType;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Slice;

import java.util.List;
import java.util.stream.Collectors;

public class ReviewConverter {

    /* ============ 가게의 리뷰 목록 (기존, 오프셋 기반) ============ */

    public static ReviewResDTO.ReviewPreViewDTO reviewPreViewDTO(Review review) {
        return ReviewResDTO.ReviewPreViewDTO.builder()
                .nickname(review.getMember().getName())
                .score(review.getScore())
                .body(review.getBody())
                .createdAt(review.getCreatedAt().toLocalDate())
                .build();
    }

    public static ReviewResDTO.ReviewPreViewListDTO reviewPreViewListDTO(Page<Review> reviewPage) {
        List<ReviewResDTO.ReviewPreViewDTO> reviewPreViewDTOList = reviewPage.stream()
                .map(ReviewConverter::reviewPreViewDTO)
                .collect(Collectors.toList());

        return ReviewResDTO.ReviewPreViewListDTO.builder()
                .isFirst(reviewPage.isFirst())
                .isLast(reviewPage.isLast())
                .totalPage(reviewPage.getTotalPages())
                .totalElements(reviewPage.getTotalElements())
                .listSize(reviewPreViewDTOList.size())
                .reviewList(reviewPreViewDTOList)
                .build();
    }

    /* ============ 내가 작성한 리뷰 목록 (커서 기반, 사진 제외) ============ */

    public static ReviewResDTO.MyReviewPreViewDTO toMyReviewPreViewDTO(Review review) {
        return ReviewResDTO.MyReviewPreViewDTO.builder()
                .reviewId(review.getId())
                .storeName(review.getStore().getName())
                .score(review.getScore())
                .body(review.getBody())
                .createdAt(review.getCreatedAt().toLocalDate())
                .build();
    }

    public static ReviewResDTO.MyReviewPreViewListDTO toMyReviewPreViewListDTO(
            Slice<Review> slice,
            ReviewSortType sortType
    ) {
        List<ReviewResDTO.MyReviewPreViewDTO> reviewList = slice.getContent().stream()
                .map(ReviewConverter::toMyReviewPreViewDTO)
                .collect(Collectors.toList());

        Long nextCursorId = null;
        Float nextCursorScore = null;

        // 다음 페이지가 있을 때 마지막 리뷰의 값으로 커서 세팅
        if (slice.hasNext() && !slice.getContent().isEmpty()) {
            Review last = slice.getContent().get(slice.getContent().size() - 1);
            nextCursorId = last.getId();
            if (sortType == ReviewSortType.SCORE) {
                nextCursorScore = last.getScore();
            }
        }

        return ReviewResDTO.MyReviewPreViewListDTO.builder()
                .reviewList(reviewList)
                .listSize(reviewList.size())
                .hasNext(slice.hasNext())
                .nextCursorId(nextCursorId)
                .nextCursorScore(nextCursorScore)
                .build();
    }
}
