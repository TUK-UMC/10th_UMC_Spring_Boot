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
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MissionService {
    private final MissionRepository missionRepository;
    private final MemberMissionRepository memberMissionRepository;

    public Page<Mission> getRegionMissionList(Long regionId, Integer page) {
        return missionRepository.findAllByRegionId(regionId, PageRequest.of(page - 1, 10));
    }

    //유저 미션 조회
    public MissionResDTO.Pagination<MissionResDTO.MyMissionDTO> getMyMissionList(
            Long memberId,
            String status,
            Integer pageSize,
            Integer pageNumber,
            String sort) {

        Sort sortInfo;
        if (sort != null){
            sortInfo = Sort.by(sort);
        } else {
            sortInfo = Sort.by("id").descending();
        }

        PageRequest pageRequest = PageRequest.of(pageNumber, pageSize, sortInfo);

        MissionStatus missionStatus = MissionStatus.valueOf(status.toUpperCase());

        Page<MemberMission> memberMissions = memberMissionRepository.findAllByMemberIdAndStatus(memberId, missionStatus, pageRequest);

        List<MissionResDTO.MyMissionDTO> data = memberMissions.map(MissionConverter::toMyMissionDTO).getContent();

        return MissionResDTO.Pagination.<MissionResDTO.MyMissionDTO>builder()
                .data(data)
                .pageNumber(memberMissions.getNumber())
                .pageSize(memberMissions.getSize())
                .build();
    }

    public Page<MissionResDTO.HomeMissionDTO> getAvailableMissionsByRegion(String regionName, Long memberId, Integer page) {
        Page<Mission> missions = missionRepository.findAvailableMissionsByRegionName(regionName, memberId, PageRequest.of(page - 1, 10));
        return missions.map(MissionConverter::toHomeMissionDTO);
    }
}
