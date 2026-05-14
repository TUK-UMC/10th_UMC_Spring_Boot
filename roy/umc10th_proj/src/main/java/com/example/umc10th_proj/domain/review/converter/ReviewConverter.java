package com.example.umc10th_proj.domain.review.converter;

import com.example.umc10th_proj.domain.review.dto.ReviewReqDTO;
import com.example.umc10th_proj.domain.review.dto.ReviewResDTO;
import com.example.umc10th_proj.domain.review.entity.Review;
import com.example.umc10th_proj.global.apiPayload.ApiResponse;
import com.example.umc10th_proj.global.apiPayload.code.GeneralSuccessCode;
import jakarta.validation.Valid;
import org.springframework.data.domain.Slice;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

public class ReviewConverter {

    public static ReviewResDTO.CreateReviewResult toCreateReviewResult(Review review) {
        return ReviewResDTO.CreateReviewResult.builder()
                .reviewId(review.getId())
                .createdAt(review.getCreatedAt())
                .build();
    }

    public static ReviewResDTO.MyReviewPreViewList toMyReviewPreViewList(Slice<Review> reviewSlice) {
        List<ReviewResDTO.MyReviewInfo> reviewInfoList = reviewSlice.getContent().stream()
                .map(review -> ReviewResDTO.MyReviewInfo.builder()
                        .reviewId(review.getId())
                        .storeName(review.getStore().getName())
                        .content(review.getContent())
                        .star(review.getStar())
                        .createdAt(review.getCreatedAt())
                        .build())
                .collect(Collectors.toList());

        Long nextCursorId = null;
        BigDecimal nextCursorStar = null;

        // 다음 페이지가 있다면 마지막 요소의 값을 다음 커서로 설정
        if (reviewSlice.hasNext() && !reviewInfoList.isEmpty()) {
            ReviewResDTO.MyReviewInfo lastElement = reviewInfoList.get(reviewInfoList.size() - 1);
            nextCursorId = lastElement.reviewId();
            nextCursorStar = lastElement.star();
        }

        return ReviewResDTO.MyReviewPreViewList.builder()
                .reviewList(reviewInfoList)
                .hasNext(reviewSlice.hasNext())
                .nextCursorId(nextCursorId)
                .nextCursorStar(nextCursorStar)
                .build();
    }
}