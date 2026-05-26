package com.example.toy.domain.mission.exception;

import com.example.toy.global.apiPayload.code.BaseErrorCode;
import com.example.toy.global.apiPayload.exception.ProjectException;

public class MissionException extends ProjectException {
    public MissionException(BaseErrorCode errorCode) {
        super(errorCode);
    }
}
