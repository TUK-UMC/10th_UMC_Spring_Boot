package com.example.umc10th2.domain.mission.service;

import com.example.umc10th2.domain.mission.converter.MissionConverter;
import com.example.umc10th2.domain.mission.dto.MissionResDTO;
import com.example.umc10th2.domain.mission.entity.Mission;
import com.example.umc10th2.domain.mission.entity.mapping.MemberMission;
import com.example.umc10th2.domain.mission.enums.Address;
import com.example.umc10th2.domain.mission.enums.MissionStatus;
import com.example.umc10th2.domain.mission.repository.MemberMissionRepository;
import com.example.umc10th2.domain.mission.repository.MissionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MissionService {

    private final MissionRepository missionRepository;
    private final MemberMissionRepository memberMissionRepository;

    // 내 미션 목록: 진행중(CHALLENGING) / 완료(COMPLETE) 필터 + 페이징
    public MissionResDTO.MyMissionPageRes getMyMissions(
            Long memberId, String statusStr, int page, int size) {

        MissionStatus status = (statusStr != null)
                ? MissionStatus.valueOf(statusStr.toUpperCase())
                : null;

        PageRequest pageable = PageRequest.of(page - 1, size,
                Sort.by(Sort.Direction.DESC, "createdAt"));

        Page<MemberMission> result =
                memberMissionRepository.findMyMissions(memberId, status, pageable);

        return MissionConverter.toMyMissionPageRes(result);
    }

    // 홈 화면: 선택된 지역에서 도전 가능한 미션 목록 + 페이징
    public MissionResDTO.HomeMissionPageRes getHomeMissions(
            Long memberId, String addressStr, int page, int size) {

        Address address = Address.valueOf(addressStr.toUpperCase());

        PageRequest pageable = PageRequest.of(page - 1, size,
                Sort.by(Sort.Direction.ASC, "deadline"));

        Page<Mission> result =
                missionRepository.findAvailableMissionsByAddress(address, memberId, pageable);

        return MissionConverter.toHomeMissionPageRes(result);
    }
}
