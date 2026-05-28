package com.example.toy.global.apiPayload.exception;

import com.example.toy.domain.member.exception.MemberException;
import com.example.toy.global.apiPayload.code.BaseErrorCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class ProjectException extends RuntimeException {
    private final BaseErrorCode errorCode;
}
