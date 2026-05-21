package com.example.umc10th.domain.member.exception.code;

import com.example.umc10th.global.apiPayload.code.BaseErrorCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum MemberErrorCode implements BaseErrorCode {
    MEMBER_NOT_FOUND(HttpStatus.NOT_FOUND, "MEMBER404_1", "해당 회원을 찾을 수 없습니다."),
    MEMBER_EMAIL_DUPLICATE(HttpStatus.CONFLICT, "MEMBER409_1", "이미 가입된 이메일입니다."),
    ;

    private final HttpStatus status;
    private final String code;
    private final String message;
}