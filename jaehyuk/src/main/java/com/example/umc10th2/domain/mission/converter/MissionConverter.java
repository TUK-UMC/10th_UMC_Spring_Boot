package com.example.umc10th2.domain.mission.converter;

import com.example.umc10th2.domain.mission.dto.MissionResDTO;
import com.example.umc10th2.domain.mission.entity.Mission;
import com.example.umc10th2.domain.mission.entity.mapping.MemberMission;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.stream.Collectors;

public class MissionConverter {

    public static MissionResDTO.MyMissionItem toMyMissionItem(MemberMission mm) {
        Mission mission = mm.getMission();
        return MissionResDTO.MyMissionItem.builder()
                .memberMissionId(mm.getId())
                .missionId(mission.getId())
                .storeName(mission.getStore().getName())
                .introduction(mission.getIntroduction())
                .point(mission.getPoint())
                .deadline(mission.getDeadline())
                .status(mm.getStatus())
                .build();
    }

    public static MissionResDTO.MyMissionPageRes toMyMissionPageRes(Page<MemberMission> page) {
        List<MissionResDTO.MyMissionItem> content = page.getContent()
                .stream()
                .map(MissionConverter::toMyMissionItem)
                .collect(Collectors.toList());

        return MissionResDTO.MyMissionPageRes.builder()
                .content(content)
                .page(page.getNumber() + 1)
                .size(page.getSize())
                .totalElements(page.getTotalElements())
                .totalPages(page.getTotalPages())
                .last(page.isLast())
                .build();
    }

    public static MissionResDTO.HomeMissionItem toHomeMissionItem(Mission m) {
        return MissionResDTO.HomeMissionItem.builder()
                .missionId(m.getId())
                .storeName(m.getStore().getName())
                .introduction(m.getIntroduction())
                .point(m.getPoint())
                .deadline(m.getDeadline())
                .address(m.getStore().getLocation().getAddress().name())
                .build();
    }

    public static MissionResDTO.HomeMissionPageRes toHomeMissionPageRes(Page<Mission> page) {
        List<MissionResDTO.HomeMissionItem> content = page.getContent()
                .stream()
                .map(MissionConverter::toHomeMissionItem)
                .collect(Collectors.toList());

        return MissionResDTO.HomeMissionPageRes.builder()
                .content(content)
                .page(page.getNumber() + 1)
                .size(page.getSize())
                .totalElements(page.getTotalElements())
                .totalPages(page.getTotalPages())
                .last(page.isLast())
                .build();
    }
}
