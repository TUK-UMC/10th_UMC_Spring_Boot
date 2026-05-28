package com.example.umc10th2.domain.mission.exception.code;

import com.example.umc10th2.global.exception.BaseErrorCode;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum MissionErrorCode implements BaseErrorCode {
    MISSION_NOT_FOUND(HttpStatus.NOT_FOUND,        "MISSION404", "존재하지 않는 미션입니다."),
    MISSION_ALREADY_CHALLENGING(HttpStatus.CONFLICT, "MISSION409", "이미 도전 중인 미션입니다.");

    private final HttpStatus status;
    private final String code;
    private final String message;
}
