package com.example.umc10th2.domain.review.controller;

import com.example.umc10th2.domain.review.dto.ReviewReqDTO;
import com.example.umc10th2.domain.review.dto.ReviewResDTO;
import com.example.umc10th2.domain.review.service.ReviewService;
import com.example.umc10th2.global.response.ApiResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@Tag(name = "Review", description = "리뷰 관련 API")
@RestController
@RequestMapping("/reviews")
@RequiredArgsConstructor
public class ReviewController {

    private final ReviewService reviewService;

    @Operation(summary = "리뷰 작성", description = "가게에 대한 리뷰를 작성합니다. (사진 제외)")
    @PostMapping("/members/{memberId}")
    public ApiResponse<ReviewResDTO.CreateRes> createReview(
            @PathVariable Long memberId,
            @RequestBody ReviewReqDTO request
    ) {
        return ApiResponse.success(reviewService.createReview(memberId, request));
    }
}
