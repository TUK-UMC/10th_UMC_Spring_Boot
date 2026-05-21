package com.example.umc10th.domain.member.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

public class MemberReqDTO {

    // 회원 조회를 위해 요청에 필요한 id값만 받습니다.
    public record GetInfo(
            @NotNull(message = "회원 ID는 필수입니다.")
            @Positive(message = "회원 ID는 0보다 커야 합니다.")
            Long id
    ){}

    // 회원가입(폼 로그인) 요청: 이름 + 이메일 + 비밀번호를 받습니다.
    public record SignUp(
            @NotBlank(message = "이름은 필수입니다.")
            @Size(max = 30, message = "이름은 30자 이하여야 합니다.")
            String name,

            @NotBlank(message = "이메일은 필수입니다.")
            @Email(message = "올바른 이메일 형식이 아닙니다.")
            @Size(max = 100, message = "이메일은 100자 이하여야 합니다.")
            String email,

            @NotBlank(message = "비밀번호는 필수입니다.")
            @Size(min = 8, max = 64, message = "비밀번호는 8자 이상 64자 이하여야 합니다.")
            String password
    ){}
}
