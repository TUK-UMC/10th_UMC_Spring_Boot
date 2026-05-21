package com.example.umc10th2.domain.review.controller;

import com.example.umc10th2.domain.review.dto.ReviewReqDTO;
import com.example.umc10th2.domain.review.dto.ReviewResDTO;
import com.example.umc10th2.domain.review.service.ReviewService;
import com.example.umc10th2.global.response.ApiResponse;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/reviews")
public class ReviewController {

    private final ReviewService reviewService;

    // ✅ 리뷰 작성 (@Valid 검증 포함)
    @Operation(summary = "리뷰 작성", description = "별점 1~5, 내용 200자 이하 검증")
    @PostMapping
    public ApiResponse<Void> createReview(
            @RequestBody @Valid ReviewReqDTO.CreateReviewRequest request) {
        reviewService.createReview(request);
        return ApiResponse.success(null);
    }

    // ✅ 내가 작성한 리뷰 조회 (커서 기반 - ID순)
    @Operation(summary = "내 리뷰 조회 - ID순 (커서 기반)",
               description = "lastId=0으로 첫 조회. 응답의 nextCursorId를 다음 요청 lastId로 사용.")
    @PostMapping("/my/by-id")
    public ApiResponse<ReviewResDTO.MyReviewCursorResponse> getMyReviewsById(
            @RequestBody @Valid ReviewReqDTO.MyReviewRequest request) {
        return ApiResponse.success(reviewService.getMyReviewsById(request));
    }

    // ✅ 내가 작성한 리뷰 조회 (커서 기반 - 별점순)
    @Operation(summary = "내 리뷰 조회 - 별점순 (커서 기반)",
               description = "첫 조회 시 lastScore=5.0, lastId=0. 응답의 nextCursorScore/nextCursorId를 다음 요청에 사용.")
    @PostMapping("/my/by-score")
    public ApiResponse<ReviewResDTO.MyReviewCursorResponse> getMyReviewsByScore(
            @RequestBody @Valid ReviewReqDTO.MyReviewRequest request) {
        return ApiResponse.success(reviewService.getMyReviewsByScore(request));
    }
}
