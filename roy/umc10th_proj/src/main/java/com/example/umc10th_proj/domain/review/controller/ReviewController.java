package com.example.umc10th_proj.domain.review.controller;

import com.example.umc10th_proj.domain.review.dto.ReviewReqDTO;
import com.example.umc10th_proj.global.apiPayload.ApiResponse;
import com.example.umc10th_proj.global.apiPayload.code.GeneralSuccessCode;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/stores")
public class ReviewController {

    // 마이 페이지 리뷰 작성 (multipart/form-data)
    @PostMapping(value = "/{storeId}/reviews", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ApiResponse<String> createReview(
            @PathVariable(name = "storeId") Long storeId,
            @ModelAttribute ReviewReqDTO.CreateReview request
    ) {
        // TODO: reviewService.createReview(storeId, request) 호출 로직 작성
        return ApiResponse.onSuccess(GeneralSuccessCode.OK, "리뷰 작성 성공");
    }
}