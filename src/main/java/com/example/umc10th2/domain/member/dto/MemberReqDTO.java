package com.example.umc10th2.domain.member.dto;

import com.example.umc10th2.domain.member.enums.Gender;
import com.example.umc10th2.domain.mission.enums.MemberMissionStatus;
import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

public class MemberReqDTO {

    @Getter
    @NoArgsConstructor
    public static class SignupRequest {

        @NotBlank(message = "이름은 필수입니다.")
        @Size(max = 20, message = "이름은 20자 이하여야 합니다.")
        private String name;

        @NotBlank(message = "이메일은 필수입니다.")
        @Email(message = "올바른 이메일 형식이 아닙니다.")
        private String email;

        @NotBlank(message = "비밀번호는 필수입니다.")
        @Size(min = 8, message = "비밀번호는 8자 이상이어야 합니다.")
        private String password;

        @NotBlank(message = "전화번호는 필수입니다.")
        @Pattern(regexp = "^010-\\d{4}-\\d{4}$",
                message = "전화번호 형식이 올바르지 않습니다. (예: 010-1234-5678)")
        private String phoneNum;

        @NotNull(message = "성별은 필수입니다.")
        private Gender gender;

        @NotNull(message = "생년월일은 필수입니다.")
        @Past(message = "생년월일은 과거여야 합니다.")
        private LocalDate birthDate;

        @NotBlank(message = "주소는 필수입니다.")
        private String address;

        private List<Long> foodCategoryIds;
        private List<Long> termIds;
    }

    @Getter
    @NoArgsConstructor
    public static class MyMissionRequest {

        @NotNull(message = "회원 ID는 필수입니다.")
        private Long memberId;

        @NotNull(message = "미션 상태는 필수입니다.")
        private MemberMissionStatus status;

        @Min(value = 0, message = "페이지는 0 이상이어야 합니다.")
        private int page = 0;
    }
}
