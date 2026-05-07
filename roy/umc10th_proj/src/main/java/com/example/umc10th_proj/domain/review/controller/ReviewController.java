package com.example.umc10th_proj.domain.review.controller;

import com.example.umc10th_proj.domain.review.converter.ReviewConverter;
import com.example.umc10th_proj.domain.review.dto.ReviewReqDTO;
import com.example.umc10th_proj.domain.review.dto.ReviewResDTO;
import com.example.umc10th_proj.domain.review.entity.Review;
import com.example.umc10th_proj.domain.review.service.ReviewService;
import com.example.umc10th_proj.global.apiPayload.ApiResponse;
import com.example.umc10th_proj.global.apiPayload.code.GeneralSuccessCode;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/stores")
public class ReviewController {

    private final ReviewService reviewService;

    @PostMapping("/{storeId}/reviews")
    public ApiResponse<ReviewResDTO.CreateReviewResult> createReview(
            @PathVariable(name = "storeId") Long storeId,
            @RequestBody ReviewReqDTO.CreateReview request
    ) {
        Review review = reviewService.createReview(storeId, 1L, request);
        return ApiResponse.onSuccess(
                GeneralSuccessCode.OK,
                ReviewConverter.toCreateReviewResult(review)
        );
    }
}