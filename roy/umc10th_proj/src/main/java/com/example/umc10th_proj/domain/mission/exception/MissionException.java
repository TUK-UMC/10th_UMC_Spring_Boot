package com.example.umc10th_proj.domain.mission.exception;

import com.example.umc10th_proj.global.apiPayload.code.BaseErrorCode;
import com.example.umc10th_proj.global.apiPayload.exception.ProjectException;

public class MissionException extends ProjectException {
    public MissionException(BaseErrorCode errorCode) {
        super(errorCode);
    }
}