package com.example.umc10th.domain.member.dto;

import lombok.Builder;

import java.time.LocalDateTime;

public class MemberResDTO {

    // 회원가입 결과: 비밀번호는 응답에 절대 포함하지 않습니다.
    @Builder
    public record SignUp(
            Long memberId,
            String name,
            String email,
            LocalDateTime createdAt
    ){}

    @Builder
    public record GetInfo(
            String name,
            String profileUrl,
            String email,
            String phoneNumber,
            Integer point
    ){}
}