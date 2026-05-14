package com.example.toy.domain.mission.converter;

import com.example.toy.domain.mission.dto.MissionResDTO;
import com.example.toy.domain.mission.entity.Mission;
import com.example.toy.domain.mission.entity.mapping.MemberMission;

import java.util.List;

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

    public static <T> MissionResDTO.Pagination<T> toPagination(
            List<T> data,
            Integer pageNumber,
            Integer pageSize
    ){
        return MissionResDTO.Pagination.<T>builder()
                .data(data)
                .pageNumber(pageNumber)
                .pageSize(pageSize)
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
