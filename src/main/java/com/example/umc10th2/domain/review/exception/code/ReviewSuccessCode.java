package com.example.umc10th2.domain.review.exception.code;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ReviewSuccessCode {
    REVIEW_CREATED("REVIEW201", "리뷰가 작성되었습니다."),
    REVIEW_FOUND("REVIEW200", "리뷰 조회에 성공하였습니다.");

    private final String code;
    private final String message;
}
