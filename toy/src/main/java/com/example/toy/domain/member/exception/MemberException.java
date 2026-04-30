package com.example.toy.domain.member.exception;

import com.example.toy.domain.member.exception.code.MemberErrorCode;
import com.example.toy.global.apiPayload.code.BaseErrorCode;
import com.example.toy.global.apiPayload.exception.ProjectException;

public class MemberException extends ProjectException {
    public MemberException(BaseErrorCode errorCode) {super(errorCode);}
}
