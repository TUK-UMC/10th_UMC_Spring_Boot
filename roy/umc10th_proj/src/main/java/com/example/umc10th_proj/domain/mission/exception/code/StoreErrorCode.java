package com.example.umc10th_proj.domain.mission.exception.code;

import com.example.umc10th_proj.global.apiPayload.code.BaseErrorCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum StoreErrorCode implements BaseErrorCode {

    NOT_FOUND(HttpStatus.NOT_FOUND, "STORE404_1", "해당 가게가 존재하지 않습니다.")
    ; // (주의) 상수 선언이 끝난 후 이 세미콜론(;)이 반드시 있어야 한다.

    // 이 아래 3개의 필드가 있어야 @Getter가 메서드를 자동 생성한다.
    private final HttpStatus status;
    private final String code;
    private final String message;
}