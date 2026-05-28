package com.example.toy.domain.member.dto;

import com.example.toy.domain.member.enums.Gender;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

public class MemberReqDTO {

    public record GetInfo(Long id){};

    public record RequestBody(Long id) {}

    @Getter
    public static class SignUp {

        @NotBlank(message = "이름은 필수입니다.")
        private String name;

        @NotBlank(message = "이메일은 필수입니다.")
        @Email(message = "이메일 형식이 올바르지 않습니다.")
        private String email;

        @NotBlank(message = "비밀번호는 필수입니다.")
        private String password;

        @NotNull(message = "성별은 필수입니다.")
        private Gender gender;

        @NotNull(message = "나이는 필수입니다.")
        private Integer age;

        private String address;

        private String specAddress;

        @NotNull(message = "약관 동의 목록은 필수입니다.")
        private List<Long> termIds;

        private List<Long> foodIds;
    }

    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    public static class Login {
        @NotBlank(message = "이메일은 필수 입력 항목입니다.")
        @Email(message = "이메일 형식이 올바르지 않습니다.")
        private String email;

        @NotBlank(message = "비밀번호는 필수 입력 항목입니다.")
        private String password;
    }
}
