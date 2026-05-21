package com.example.umc10th.domain.mission.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;

public class MissionReqDTO {

    public record CreateMission(
            @NotNull(message = "마감일은 필수입니다.")
            LocalDate deadline,

            @NotNull(message = "보상 포인트는 필수입니다.")
            @Positive(message = "보상 포인트는 0보다 커야 합니다.")
            Integer point,

            @NotBlank(message = "미션 조건은 필수입니다.")
            @Size(max = 200, message = "미션 조건은 200자 이하여야 합니다.")
            String conditional
    ){}

    // 내가 진행중인 미션 조회를 위한 Request Body
    public record GetMyMissions(
            @NotNull(message = "회원 ID는 필수입니다.")
            @Positive(message = "회원 ID는 0보다 커야 합니다.")
            Long memberId
    ){}
}
