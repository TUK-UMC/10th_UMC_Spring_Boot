package com.example.toy.domain.mission.converter;

import com.example.toy.domain.mission.dto.MissionResDTO;
import com.example.toy.domain.mission.entity.Mission;
import com.example.toy.domain.mission.entity.mapping.MemberMission;

public class MissionConverter {

    public static MissionResDTO.MyMissionDTO toMyMissionDTO(MemberMission memberMission) {
        return MissionResDTO.MyMissionDTO.builder()
                .reward(memberMission.getMission().getReward())
                .storeName(memberMission.getMission().getStore().getName())
                .missionSpec(memberMission.getMission().getMissionSpec())
                .status(memberMission.getStatus().name())
                .createdAt(memberMission.getCreatedAt())
                .build();
    }

    public static MissionResDTO.HomeMissionDTO toHomeMissionDTO(Mission mission) {
        return MissionResDTO.HomeMissionDTO.builder()
                .storeName(mission.getStore().getName())
                .missionSpec(mission.getMissionSpec())
                .reward(mission.getReward())
                .build();
    }
}
