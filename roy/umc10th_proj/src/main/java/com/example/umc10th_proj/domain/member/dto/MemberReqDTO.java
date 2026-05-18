package com.example.umc10th_proj.domain.member.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Getter;
import java.time.LocalDate;
import java.util.List;

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
        @NotBlank(message = "이메일은 필수입니다.")
        private String email;

        @NotBlank(message = "비밀번호는 필수입니다.")
        private String password;

        @NotBlank(message = "이름은 필수입니다.")
        private String name;

        @NotNull(message = "성별을 선택해주세요.")
        private Integer gender; // 1: 남, 2: 여, 3: 선택안함

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

        @Size(min = 1, message = "최소 1개 이상의 약관에 동의해야 합니다.")
        private List<Long> agreeTerms; // 동의한 약관 ID 리스트

        @Size(min = 1, message = "최소 1개 이상의 선호 음식을 선택해야 합니다.")
        private List<Long> preferFoods; // 선호 음식 ID 리스트
    }
}