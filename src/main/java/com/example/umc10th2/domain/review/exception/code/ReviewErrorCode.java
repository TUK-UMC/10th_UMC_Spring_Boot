package com.example.umc10th2.domain.review.exception.code;

import com.example.umc10th2.global.exception.BaseErrorCode;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum ReviewErrorCode implements BaseErrorCode {
    REVIEW_NOT_FOUND(HttpStatus.NOT_FOUND, "REVIEW404", "존재하지 않는 리뷰입니다."),
    STORE_NOT_FOUND(HttpStatus.NOT_FOUND,  "STORE404",  "존재하지 않는 가게입니다.");

    private final HttpStatus status;
    private final String code;
    private final String message;
}
