package com.example.umc10th2.domain.mission.exception;

import com.example.umc10th2.domain.mission.exception.code.MissionErrorCode;
import com.example.umc10th2.global.exception.GeneralException;

public class MissionException extends GeneralException {
    public MissionException(MissionErrorCode errorCode) {
        super(errorCode);
    }
}
