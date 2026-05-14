package com.example.umc10th.domain.review.controller;

import com.example.umc10th.domain.review.converter.ReviewConverter;
import com.example.umc10th.domain.review.dto.ReviewReqDTO;
import com.example.umc10th.domain.review.dto.ReviewResDTO;
import com.example.umc10th.domain.review.entity.Review;
import com.example.umc10th.domain.review.enums.ReviewSortType;
import com.example.umc10th.domain.review.exception.code.ReviewSuccessCode;
import com.example.umc10th.domain.review.service.ReviewService;
import com.example.umc10th.global.apiPayload.ApiResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Slice;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class ReviewController {

    private final ReviewService reviewService;

    /**
     * 특정 가게의 리뷰 목록 조회 (오프셋 기반)
     */
    @GetMapping("/api/stores/{storeId}/reviews")
    public ApiResponse<ReviewResDTO.ReviewPreViewListDTO> getReviewList(
            @PathVariable(name = "storeId") Long storeId,
            @RequestParam(name = "page", defaultValue = "1") Integer page
    ) {
        Page<Review> reviewPage = reviewService.getReviewList(storeId, page);
        return ApiResponse.onSuccess(ReviewSuccessCode.OK, ReviewConverter.reviewPreViewListDTO(reviewPage));
    }

    /**
     * 내가 작성한 리뷰 목록 조회 (커서 기반)
     *  - 사용자 ID 는 Request Body 로 받음
     *  - sortType: ID(기본, 최신순) / SCORE(별점 내림차순)
     *  - cursorId, cursorScore 는 직전 응답의 nextCursor 값을 그대로 보내면 됨
     *  - 사진(ReviewPhoto)은 응답에 포함되지 않음
     */
    @GetMapping("/api/v1/members/me/reviews")
    public ApiResponse<ReviewResDTO.MyReviewPreViewListDTO> getMyReviews(
            @RequestBody @Valid ReviewReqDTO.GetMyReviews request,
            @RequestParam(name = "sortType", defaultValue = "ID") ReviewSortType sortType,
            @RequestParam(name = "cursorId", required = false) Long cursorId,
            @RequestParam(name = "cursorScore", required = false) Float cursorScore,
            @RequestParam(name = "size", defaultValue = "10") int size
    ) {
        Slice<Review> slice;
        if (sortType == ReviewSortType.SCORE) {
            slice = reviewService.getMyReviewsByScore(request.memberId(), cursorScore, cursorId, size);
        } else {
            slice = reviewService.getMyReviewsById(request.memberId(), cursorId, size);
        }

        return ApiResponse.onSuccess(
                ReviewSuccessCode.MY_REVIEW_OK,
                ReviewConverter.toMyReviewPreViewListDTO(slice, sortType)
        );
    }
}