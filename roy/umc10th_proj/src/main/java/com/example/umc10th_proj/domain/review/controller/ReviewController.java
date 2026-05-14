// src/main/java/com/example/umc10th_proj/domain/review/controller/ReviewController.java
package com.example.umc10th_proj.domain.review.controller;

import com.example.umc10th_proj.domain.review.converter.ReviewConverter;
import com.example.umc10th_proj.domain.review.dto.ReviewReqDTO;
import com.example.umc10th_proj.domain.review.dto.ReviewResDTO;
import com.example.umc10th_proj.domain.review.entity.Review;
import com.example.umc10th_proj.domain.review.service.ReviewService;
import com.example.umc10th_proj.global.apiPayload.ApiResponse;
import com.example.umc10th_proj.global.apiPayload.code.GeneralSuccessCode;
import jakarta.validation.Valid; // 반드시 추가할 것
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
            @RequestBody @Valid ReviewReqDTO.CreateReview request // 여기에 @Valid 추가
    ) {
        Review review = reviewService.createReview(storeId, 1L, request);
        return ApiResponse.onSuccess(
                GeneralSuccessCode.OK,
                ReviewConverter.toCreateReviewResult(review)
        );
    }
}