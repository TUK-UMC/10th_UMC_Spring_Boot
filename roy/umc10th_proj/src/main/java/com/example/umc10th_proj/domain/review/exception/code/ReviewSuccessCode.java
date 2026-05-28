package com.example.umc10th_proj.domain.review.exception.code;

import com.example.umc10th_proj.global.apiPayload.code.BaseSuccessCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum ReviewSuccessCode implements BaseSuccessCode {

    CREATED(HttpStatus.CREATED, "REVIEW201_1", "리뷰가 성공적으로 작성되었습니다."),
    OK(HttpStatus.OK, "REVIEW200_1", "리뷰 목록을 성공적으로 조회했습니다."),
    ;

    private final HttpStatus status;
    private final String code;
    private final String message;
}