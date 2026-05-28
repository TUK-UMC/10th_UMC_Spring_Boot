package com.example.umc10th2.domain.mission.exception.code;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum MissionSuccessCode {
    MISSION_FOUND("MISSION200", "미션 조회에 성공하였습니다.");

    private final String code;
    private final String message;
}
