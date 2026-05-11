package com.example.toy.domain.mission.service;

import com.example.toy.domain.mission.converter.MissionConverter;
import com.example.toy.domain.mission.dto.MissionResDTO;
import com.example.toy.domain.mission.entity.Mission;
import com.example.toy.domain.mission.entity.mapping.MemberMission;
import com.example.toy.domain.mission.enums.MissionStatus;
import com.example.toy.domain.mission.repository.MemberMissionRepository;
import com.example.toy.domain.mission.repository.MissionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MissionService {
    private final MissionRepository missionRepository;
    private final MemberMissionRepository memberMissionRepository;

    public Page<Mission> getRegionMissionList(Long regionId, Integer page) {
        return missionRepository.findAllByRegionId(regionId, PageRequest.of(page - 1, 10));
    }

    public Page<MissionResDTO.MyMissionDTO> getMyMissionList(Long memberId, String status, Integer page) {
        MissionStatus missionStatus = MissionStatus.valueOf(status.toUpperCase());
        Page<MemberMission> memberMissions = memberMissionRepository.findAllByMemberIdAndStatus(memberId, missionStatus, PageRequest.of(page - 1, 10));
        return memberMissions.map(MissionConverter::toMyMissionDTO);
    }

    public Page<MissionResDTO.HomeMissionDTO> getAvailableMissionsByRegion(String regionName, Long memberId, Integer page) {
        Page<Mission> missions = missionRepository.findAvailableMissionsByRegionName(regionName, memberId, PageRequest.of(page - 1, 10));
        return missions.map(MissionConverter::toHomeMissionDTO);
    }
}
