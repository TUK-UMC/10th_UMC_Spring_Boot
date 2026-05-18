package com.example.umc10th_proj.domain.mission.dto;

import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;

import java.time.LocalDate;

public class MissionReqDTO {
    // 가게 미션 생성
    public record CreateMission(
            @NotNull(message = "마감일은 필수입니다")
            @FutureOrPresent(message = "마감일은 현재 혹은 미래로 해야함")
            LocalDate deadline,

            @NotNull(message = "포인트는 필수입니다")
            @Min(value = 1, message = "포인트는 최소 1이상이어야 함")
            Integer point,

            @NotBlank(message = "미션 조건은 필수입니다")
            String conditional
    ){}

    @Getter
    public static class CompleteMissionDto {
        private String managerNumber;
    }
}