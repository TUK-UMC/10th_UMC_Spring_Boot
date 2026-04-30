package com.example.umc10th_proj.global.apiPayload.exception;

import lombok.Getter;
import com.example.umc10th_proj.global.apiPayload.code.BaseErrorCode;

@Getter
public class ProjectException extends RuntimeException {

    private final BaseErrorCode errorCode;

    // @RequiredArgsConstructor를 빼고 직접 생성자를 작성하여 부모 생성자(super) 호출
    public ProjectException(BaseErrorCode errorCode) {
        super(errorCode.getMessage());
        this.errorCode = errorCode;
    }
}