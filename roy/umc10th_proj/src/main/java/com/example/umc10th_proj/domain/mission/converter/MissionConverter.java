package com.example.umc10th_proj.domain.mission.converter;

import com.example.umc10th_proj.domain.mission.dto.MissionResDTO;
import com.example.umc10th_proj.domain.mission.entity.Mission;
import com.example.umc10th_proj.domain.mission.entity.mapping.MemberMission;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.stream.Collectors;

public class MissionConverter {

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