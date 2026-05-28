package com.example.umc10th_proj.domain.mission.dto;

import lombok.Builder;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public class MissionResDTO {

    @Builder
    public record CreateMissionResult(
            Long missionId,
            LocalDateTime createdAt
    ) {}

    @Builder
    public record GetMission(
            Long missionId,
            Integer point,
            String conditional,
            LocalDate deadline,
            String status,
            LocalDateTime createAt
    ) {}

    @Builder
    public record Pagination<T>(
            List<T> data,
            Integer pageNumber,
            Integer pageSize
    ){}

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