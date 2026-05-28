package com.example.umc10th2.domain.mission.converter;

import com.example.umc10th2.domain.mission.dto.MissionResDTO;
import com.example.umc10th2.domain.mission.entity.mapping.MemberMission;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.stream.Collectors;

public class MissionConverter {

    public static MissionResDTO.MyMissionResponse toMyMissionResponse(MemberMission mm) {
        return MissionResDTO.MyMissionResponse.builder()
                .memberMissionId(mm.getId())
                .missionId(mm.getMission().getId())
                .storeName(mm.getMission().getStore().getName())
                .missionSpec(mm.getMission().getMissionSpec())
                .point(mm.getMission().getPoint())
                .deadline(mm.getMission().getDeadline())
                .status(mm.getStatus().name())
                .build();
    }

    public static MissionResDTO.MyMissionPageResponse toMyMissionPageResponse(Page<MemberMission> page) {
        List<MissionResDTO.MyMissionResponse> list = page.getContent().stream()
                .map(MissionConverter::toMyMissionResponse)
                .collect(Collectors.toList());

        return MissionResDTO.MyMissionPageResponse.builder()
                .missions(list)
                .currentPage(page.getNumber())
                .totalPages(page.getTotalPages())
                .totalElements(page.getTotalElements())
                .isFirst(page.isFirst())
                .isLast(page.isLast())
                .build();
    }
}
