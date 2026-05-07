package com.example.umc10th_proj.domain.mission.dto;

import lombok.Builder;

import java.time.LocalDate;
import java.util.List;

public class MissionResDTO {

    // 홈화면 미션 목록
    @Builder
    public record HomeMissionListResult(
            List<HomeMissionInfo> missionList,
            Integer listSize,
            Integer totalPage,
            Long totalElements,
            Boolean isFirst,
            Boolean isLast
    ) {}

    @Builder
    public record HomeMissionInfo(
            Long missionId,
            String storeName,
            Integer point,
            String conditional,
            LocalDate deadline
    ) {}

    // 내 미션 목록 (진행중/완료)
    @Builder
    public record MyMissionListResult(
            List<MyMissionInfo> missionList,
            Integer listSize,
            Integer totalPage,
            Long totalElements,
            Boolean isFirst,
            Boolean isLast
    ) {}

    @Builder
    public record MyMissionInfo(
            Long memberMissionId,
            String storeName,
            Integer point,
            String conditional,
            Boolean isComplete
    ) {}
}