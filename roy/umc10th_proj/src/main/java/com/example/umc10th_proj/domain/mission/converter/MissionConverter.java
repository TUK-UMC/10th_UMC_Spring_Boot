package com.example.umc10th_proj.domain.mission.converter;

import com.example.umc10th_proj.domain.mission.dto.MissionReqDTO;
import com.example.umc10th_proj.domain.mission.dto.MissionResDTO;
import com.example.umc10th_proj.domain.mission.entity.Mission;
import com.example.umc10th_proj.domain.mission.entity.Store;
import com.example.umc10th_proj.domain.mission.entity.mapping.MemberMission;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.stream.Collectors;

public class MissionConverter {

    // 페이지네이션 틀 생성
    public static <T> MissionResDTO.Pagination<T> toPagination(
            List<T> data,
            Integer pageNumber,
            Integer pageSize
    ) {
        return MissionResDTO.Pagination.<T>builder()
                .data(data)
                .pageNumber(pageNumber)
                .pageSize(pageSize)
                .build();
    }

    public static Mission toMission(
            Store store,
            MissionReqDTO.CreateMission dto)
    {
        return Mission.builder()
                .store(store)
                .conditional(dto.conditional())
                .point(dto.point())
                .deadline(dto.deadline())
                .build();
    }

    // 가게 내 미션 조회 변환 로직
    public static MissionResDTO.GetMission toGetMission(Mission mission) {
        return MissionResDTO.GetMission.builder()
                .conditional(mission.getConditional())
                .point(mission.getPoint())
                .missionId(mission.getId())
                .build();
    }

    public static MissionResDTO.HomeMissionInfo toHomeMissionInfo(Mission m) {
        return MissionResDTO.HomeMissionInfo.builder()
                .missionId(m.getId())
                .storeName(m.getStore().getName())
                .point(m.getPoint())
                .conditional(m.getConditional())
                .deadline(m.getDeadline())
                .build();
    }

    public static MissionResDTO.HomeMissionListResult toHomeMissionListResult(Page<Mission> page) {
        List<MissionResDTO.HomeMissionInfo> list = page.stream()
                .map(MissionConverter::toHomeMissionInfo)
                .collect(Collectors.toList());
        return MissionResDTO.HomeMissionListResult.builder()
                .missionList(list)
                .listSize(list.size())
                .totalPage(page.getTotalPages())
                .totalElements(page.getTotalElements())
                .isFirst(page.isFirst())
                .isLast(page.isLast())
                .build();
    }

    public static MissionResDTO.MyMissionInfo toMyMissionInfo(MemberMission mm) {
        return MissionResDTO.MyMissionInfo.builder()
                .memberMissionId(mm.getId())
                .storeName(mm.getMission().getStore().getName())
                .point(mm.getMission().getPoint())
                .conditional(mm.getMission().getConditional())
                .isComplete(mm.getIsComplete())
                .build();
    }

    public static MissionResDTO.MyMissionListResult toMyMissionListResult(Page<MemberMission> page) {
        List<MissionResDTO.MyMissionInfo> list = page.stream()
                .map(MissionConverter::toMyMissionInfo)
                .collect(Collectors.toList());
        return MissionResDTO.MyMissionListResult.builder()
                .missionList(list)
                .listSize(list.size())
                .totalPage(page.getTotalPages())
                .totalElements(page.getTotalElements())
                .isFirst(page.isFirst())
                .isLast(page.isLast())
                .build();
    }
}