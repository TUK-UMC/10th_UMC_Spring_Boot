package com.example.umc10th2.domain.review.controller;

import com.example.umc10th2.domain.review.dto.ReviewReqDTO;
import com.example.umc10th2.global.response.ApiResponse;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/members")
public class ReviewController {

    @PostMapping("/{memberId}/reviews")
    public ApiResponse<?> createReview(
            @PathVariable Long memberId,
            @RequestBody ReviewReqDTO request
    ) {
        return ApiResponse.success(null);
    }
}