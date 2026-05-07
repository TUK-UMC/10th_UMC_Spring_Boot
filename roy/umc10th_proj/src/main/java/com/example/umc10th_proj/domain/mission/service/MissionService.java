package com.example.umc10th_proj.domain.mission.service;

import com.example.umc10th_proj.domain.mission.entity.Mission;
import com.example.umc10th_proj.domain.mission.entity.mapping.MemberMission;
import com.example.umc10th_proj.domain.mission.enums.Address;
import com.example.umc10th_proj.domain.mission.repository.MemberMissionRepository;
import com.example.umc10th_proj.domain.mission.repository.MissionRepository;
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

    // 홈화면: 지역별 도전 가능한 미션 목록 (페이징)
    public Page<Mission> getHomeMissions(Address address, Integer page) {
        return missionRepository.findAvailableMissionsByAddress(
                address, PageRequest.of(page, 10)
        );
    }

    // 내 미션 목록 조회 (진행중 + 완료, 페이징)
    public Page<MemberMission> getMyMissions(Long memberId, Integer page) {
        return memberMissionRepository.findByMemberId(
                memberId, PageRequest.of(page, 10)
        );
    }
}