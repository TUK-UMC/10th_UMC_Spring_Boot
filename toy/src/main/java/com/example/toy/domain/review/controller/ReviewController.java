package com.example.toy.domain.review.controller;

import com.example.toy.domain.review.converter.ReviewConverter;
import com.example.toy.domain.review.dto.ReviewReqDTO;
import com.example.toy.domain.review.dto.ReviewResDTO;
import com.example.toy.domain.review.entity.Review;
import com.example.toy.domain.review.service.ReviewService;
import com.example.toy.global.apiPayload.ApiResponse;
import com.example.toy.global.apiPayload.code.BaseSuccessCode;
import com.example.toy.global.apiPayload.code.GeneralSuccessCode;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/reviews")
public class ReviewController {
    private final ReviewService reviewService;

    @GetMapping("/members/{memberId}")
    public ApiResponse<ReviewResDTO.Pagination<ReviewResDTO.GetReviewDTO>> getMyReviews(
            @PathVariable Long memberId,
            @RequestParam Integer pageSize,
            @RequestParam String cursor) {
        BaseSuccessCode code = GeneralSuccessCode.OK;
        return ApiResponse.onSuccess(code, reviewService.getReviewList(memberId, pageSize, cursor));
    }

    @PostMapping("/stores/{storeId}")
    public ApiResponse<ReviewResDTO.CreateReviewResultDTO> createReview(
            @PathVariable Long storeId,
            @RequestBody @Valid ReviewReqDTO.CreateReviewDTO request) {
        Review review = reviewService.createReview(storeId, request);
        return ApiResponse.onSuccess(GeneralSuccessCode.OK, ReviewConverter.toCreateReviewResultDTO(review));
    }
}
