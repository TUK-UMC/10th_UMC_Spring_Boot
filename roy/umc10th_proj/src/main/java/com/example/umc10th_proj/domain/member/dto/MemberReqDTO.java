package com.example.umc10th_proj.domain.member.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

public class MemberReqDTO {

    // 마이페이지 조회용
    @Builder
    public record GetInfo(
            Long id
    ) {}

    // 진행중인 미션 조회용
    @Getter
    public static class GetMyInProgressMissionsDto {
        @NotNull(message = "사용자 ID는 필수입니다.")
        private Long memberId;
    }

    // 로그인용
    @Getter
    public static class LoginDto {
        @NotBlank(message = "이메일은 필수입니다.")
        private String email;

        @NotBlank(message = "비밀번호는 필수입니다.")
        private String password;
    }

    // 회원가입용
    @Getter
    public static class JoinDto {

        @NotBlank(message = "이메일은 필수입니다.")
        private String email;

        @NotBlank(message = "비밀번호는 필수입니다.")
        private String password;

        @NotBlank(message = "이름은 필수입니다.")
        private String name;

        @NotNull(message = "성별을 선택해주세요.")
        private Integer gender;

        @NotNull(message = "생년은 필수입니다.")
        private Integer birthYear;

        @NotNull(message = "생월은 필수입니다.")
        private Integer birthMonth;

        @NotNull(message = "생일은 필수입니다.")
        private Integer birthDay;

        @NotBlank(message = "주소는 필수입니다.")
        private String address;

        @NotBlank(message = "상세 주소는 필수입니다.")
        private String detailAddress;

        @NotNull(message = "약관 동의 목록은 필수입니다.")
        @Size(min = 1, message = "최소 1개 이상의 약관에 동의해야 합니다.")
        private List<Long> agreeTerms;

        @NotNull(message = "선호 음식 목록은 필수입니다.")
        @Size(min = 1, message = "최소 1개 이상의 선호 음식을 선택해야 합니다.")
        private List<Long> preferFoods;
    }
}