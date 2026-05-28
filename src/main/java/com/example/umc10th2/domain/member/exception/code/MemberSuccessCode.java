package com.example.umc10th2.domain.member.exception.code;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum MemberSuccessCode {
    SIGNUP_SUCCESS("MEMBER201", "회원가입에 성공하였습니다.");

    private final String code;
    private final String message;
}
