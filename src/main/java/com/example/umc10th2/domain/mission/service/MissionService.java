package com.example.umc10th2.domain.mission.service;

import com.example.umc10th2.domain.member.entity.Member;
import com.example.umc10th2.domain.member.exception.MemberException;
import com.example.umc10th2.domain.member.exception.code.MemberErrorCode;
import com.example.umc10th2.domain.member.repository.MemberRepository;
import com.example.umc10th2.domain.mission.dto.MissionReqDTO;
import com.example.umc10th2.domain.mission.dto.MissionResDTO;
import com.example.umc10th2.domain.mission.entity.mapping.MemberMission;
import com.example.umc10th2.domain.mission.enums.MemberMissionStatus;
import com.example.umc10th2.domain.mission.repository.MemberMissionRepository;
import com.example.umc10th2.domain.mission.converter.MissionConverter;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MissionService {

    private final MemberMissionRepository memberMissionRepository;
    private final MemberRepository memberRepository;

    // ✅ 내가 진행중인 미션 조회 (오프셋 기반 페이지네이션)
    public MissionResDTO.MyMissionPageResponse getMyMissions(MissionReqDTO.MyMissionRequest request) {
        // 회원 존재 여부 확인
        memberRepository.findById(request.getMemberId())
                .orElseThrow(() -> new MemberException(MemberErrorCode.MEMBER_NOT_FOUND));

        PageRequest pageable = PageRequest.of(request.getPage(), 10);
        Page<MemberMission> page = memberMissionRepository.findByMemberIdAndStatus(
                request.getMemberId(), request.getStatus(), pageable);

        return MissionConverter.toMyMissionPageResponse(page);
    }
}
