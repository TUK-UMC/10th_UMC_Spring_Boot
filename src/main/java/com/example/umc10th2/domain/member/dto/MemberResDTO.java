package com.example.umc10th2.domain.member.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

public class MemberResDTO {

    @Getter
    @Builder
    @AllArgsConstructor
    public static class SignupResponse {
        private Long memberId;
        private String name;
        private String email;
    }
}
