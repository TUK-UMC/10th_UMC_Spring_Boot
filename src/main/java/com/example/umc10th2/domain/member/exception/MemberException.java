package com.example.umc10th2.domain.member.exception;

import com.example.umc10th2.domain.member.exception.code.MemberErrorCode;
import com.example.umc10th2.global.exception.GeneralException;

public class MemberException extends GeneralException {
    public MemberException(MemberErrorCode errorCode) {
        super(errorCode);
    }
}
