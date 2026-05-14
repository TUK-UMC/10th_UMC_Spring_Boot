package com.example.umc10th_proj.domain.member.dto;

import lombok.Builder;
import lombok.Getter;
import java.time.LocalDate;

public class MemberReqDTO {

    // 기존에 있던 GetInfo 레코드
    @Builder
    public record GetInfo(
            Long id
    ){}

    @Getter
    public static class GetMyInProgressMissionsDto {
        private Long memberId;
    }

    // 새로 추가된 회원가입용 DTO
    @Getter
    public static class JoinDto {
        private String email;
        private String name;
        private String gender;
        private LocalDate birthday;
        private String phoneNumber;
        private String address;
    }
}