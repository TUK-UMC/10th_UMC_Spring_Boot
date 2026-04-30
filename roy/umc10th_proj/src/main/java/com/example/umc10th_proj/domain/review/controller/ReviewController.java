package com.example.umc10th_proj.domain.review.controller;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import lombok.RequiredArgsConstructor;

import com.example.umc10th_proj.global.apiPayload.ApiResponse;
import com.example.umc10th_proj.domain.review.dto.ReviewReqDTO;
import com.example.umc10th_proj.domain.review.dto.ReviewResDTO;
import com.example.umc10th_proj.domain.review.service.ReviewService;
import com.example.umc10th_proj.domain.review.exception.code.ReviewSuccessCode;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/stores")
public class ReviewController {

    private final ReviewService reviewService;

    @PostMapping(value = "/{store-id}/reviews", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ApiResponse<ReviewResDTO.CreateResult> createReview(
            @PathVariable("store-id") Long storeId,
            @ModelAttribute ReviewReqDTO.CreateReview request
    ) {
        // TODO: Spring Security 적용 전 임시 유저 ID
        Long memberId = 1L;

        // 서비스 계층으로 데이터 전달
        ReviewResDTO.CreateResult result = reviewService.createReview(memberId, storeId, request);

        return ApiResponse.onSuccess(ReviewSuccessCode.REVIEW_CREATED, result);
    }
}