package com.example.umc10th2.domain.member.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;

public class MemberResDTO {

    @Getter
    @Builder
    @AllArgsConstructor
    public static class SignupResponse {
        private Long memberId;
        private String name;
        private String email;
    }

    // ✅ 로그인 응답 - JWT 토큰 반환
    @Getter
    @Builder
    @AllArgsConstructor
    public static class LoginResponse {
        private String accessToken;
        private String tokenType;
    }

    // ✅ 마이페이지 응답
    @Getter
    @Builder
    @AllArgsConstructor
    public static class MyPageResponse {
        private Long memberId;
        private String name;
        private String email;
        private String phoneNum;
        private String address;
        private LocalDate birthDate;
        private Integer point;
    }
}
