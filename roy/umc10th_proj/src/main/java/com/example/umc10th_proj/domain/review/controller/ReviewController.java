package com.example.umc10th_proj.domain.review.controller;

import com.example.umc10th_proj.domain.review.converter.ReviewConverter;
import com.example.umc10th_proj.domain.review.dto.ReviewReqDTO;
import com.example.umc10th_proj.domain.review.dto.ReviewResDTO;
import com.example.umc10th_proj.domain.review.entity.Review;
import com.example.umc10th_proj.domain.review.exception.code.ReviewSuccessCode;
import com.example.umc10th_proj.domain.review.service.ReviewService;
import com.example.umc10th_proj.global.apiPayload.ApiResponse;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@Validated
@RequestMapping("/api/v1/stores")
public class ReviewController {

    private final ReviewService reviewService;

    // 리뷰 작성
    // TODO: 9주차에서 @AuthenticationPrincipal 로 memberId 추출 예정
    @PostMapping("/{storeId}/reviews")
    public ApiResponse<ReviewResDTO.CreateReviewResult> createReview(
            @PathVariable(name = "storeId") Long storeId,
            @RequestParam @NotNull Long memberId,
            @RequestBody @Valid ReviewReqDTO.CreateReview request
    ) {
        Review review = reviewService.createReview(storeId, memberId, request);
        return ApiResponse.onSuccess(
                ReviewSuccessCode.CREATED,
                ReviewConverter.toCreateReviewResult(review)
        );
    }
}