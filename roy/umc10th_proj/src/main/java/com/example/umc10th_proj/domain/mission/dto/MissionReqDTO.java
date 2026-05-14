package com.example.umc10th_proj.domain.mission.dto;

import lombok.Getter;

import java.time.LocalDate;

public class MissionReqDTO {
    // 가게 미션 생성
    public record CreateMission(
            LocalDate deadline,
            Integer point,
            String conditional
    ){}

    @Getter
    public static class CompleteMissionDto {
        private String managerNumber;
    }
}