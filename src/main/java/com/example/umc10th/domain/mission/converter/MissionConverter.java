package com.example.umc10th.domain.mission.converter;

import com.example.umc10th.domain.mission.dto.MissionResDTO;
import com.example.umc10th.domain.mission.enums.MissionStatus;
import com.example.umc10th.domain.mission.entity.mapping.MemberMission;
import org.springframework.data.domain.Page;

import java.util.List;

public class MissionConverter {

    private static String toStatusText(MissionStatus status) {
        return switch (status) {
            case ONGOING -> "진행중";
            case COMPLETED -> "성공";
        };
    }

    public static MissionResDTO.MyMissionPreViewDTO toMyMissionPreViewDTO(MemberMission memberMission) {
        return MissionResDTO.MyMissionPreViewDTO.builder()
                .missionId(memberMission.getMission().getId())
                .storeName(memberMission.getMission().getStore().getName())
                .rewardPoint(memberMission.getMission().getReward())
                .missionSpec(memberMission.getMission().getContent())
                .status(toStatusText(memberMission.getStatus()))
                .build();
    }

    public static MissionResDTO.MyMissionPreViewListDTO toMyMissionPreViewListDTO(
            Page<MemberMission> page
    ) {
        List<MissionResDTO.MyMissionPreViewDTO> missionList = page.getContent().stream()
                .map(MissionConverter::toMyMissionPreViewDTO)
                .toList();

        return MissionResDTO.MyMissionPreViewListDTO.builder()
                .missionList(missionList)
                .listSize(missionList.size())
                .totalPage(page.getTotalPages())
                .totalElements(page.getTotalElements())
                .isFirst(page.isFirst())
                .isLast(page.isLast())
                .build();
    }
}