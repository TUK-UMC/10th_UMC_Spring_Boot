package com.example.toy.domain.mission.exception.code;

import com.example.toy.global.apiPayload.code.BaseErrorCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum MissionErrorCode implements BaseErrorCode {

    INVALID_MISSION_STATUS(HttpStatus.BAD_REQUEST, "MISSION400_1", "올바르지 않은 미션 상태값입니다.");

    private final HttpStatus status;
    private final String code;
    private final String message;
}
