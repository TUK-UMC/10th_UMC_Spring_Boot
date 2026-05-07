package com.example.umc10th2.domain.member.dto;

import lombok.Builder;
import lombok.Getter;

public class MemberResDTO {

    @Getter
    @Builder
    public static class MyPageRes {
        private String nickname;
        private String email;
        private String phoneNum;
        private Integer point;
    }
}
