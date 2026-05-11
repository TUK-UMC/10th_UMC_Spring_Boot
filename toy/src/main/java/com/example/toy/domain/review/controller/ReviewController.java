package com.example.toy.domain.review.controller;

import com.example.toy.domain.review.converter.ReviewConverter;
import com.example.toy.domain.review.dto.ReviewReqDTO;
import com.example.toy.domain.review.dto.ReviewResDTO;
import com.example.toy.domain.review.entity.Review;
import com.example.toy.domain.review.service.ReviewService;
import com.example.toy.global.apiPayload.ApiResponse;
import com.example.toy.global.apiPayload.code.BaseSuccessCode;
import com.example.toy.global.apiPayload.code.GeneralSuccessCode;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/reviews")
public class ReviewController {
    private final ReviewService reviewService;

    @GetMapping("/members/{memberId}")
    public ApiResponse<Page<Review>> getMyReviews(@PathVariable Long memberId, @RequestParam Integer page) {
        BaseSuccessCode code = GeneralSuccessCode.OK;
        return ApiResponse.onSuccess(code, reviewService.getReviewList(memberId, page));
    }

    @PostMapping("/stores/{storeId}")
    public ApiResponse<ReviewResDTO.CreateReviewResultDTO> createReview(
            @PathVariable Long storeId,
            @RequestBody ReviewReqDTO.CreateReviewDTO request) {
        Review review = reviewService.createReview(storeId, request);
        return ApiResponse.onSuccess(GeneralSuccessCode.OK, ReviewConverter.toCreateReviewResultDTO(review));
    }
}
