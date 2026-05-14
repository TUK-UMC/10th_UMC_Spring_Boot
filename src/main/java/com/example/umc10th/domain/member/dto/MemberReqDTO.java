package com.example.umc10th.domain.member.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public class MemberReqDTO {

    // 회원 조회를 위해 요청에 필요한 id값만 받습니다.
    public record GetInfo(
            @NotNull(message = "회원 ID는 필수입니다.")
            @Positive(message = "회원 ID는 0보다 커야 합니다.")
            Long id
    ){}
}
