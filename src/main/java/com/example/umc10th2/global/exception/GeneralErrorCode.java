package com.example.umc10th2.global.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum GeneralErrorCode implements BaseErrorCode {
    UNAUTHORIZED(HttpStatus.UNAUTHORIZED, "AUTH401", "로그인이 필요합니다."),
    FORBIDDEN(HttpStatus.FORBIDDEN,       "AUTH403", "접근 권한이 없습니다.");

    private final HttpStatus status;
    private final String code;
    private final String message;
}
