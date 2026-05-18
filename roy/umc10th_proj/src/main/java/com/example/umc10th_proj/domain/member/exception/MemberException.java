package com.example.umc10th_proj.domain.member.exception;

import com.example.umc10th_proj.global.apiPayload.code.BaseErrorCode;
import com.example.umc10th_proj.global.apiPayload.exception.ProjectException;

public class MemberException extends ProjectException {
    public MemberException(BaseErrorCode errorCode) {
        super(errorCode);
    }
}
