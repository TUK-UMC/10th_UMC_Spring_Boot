package com.example.umc10th_proj.domain.mission.service;

import com.example.umc10th_proj.domain.mission.dto.MissionReqDTO;
import com.example.umc10th_proj.domain.mission.dto.MissionResDTO;
import com.example.umc10th_proj.domain.mission.entity.Mission;
import com.example.umc10th_proj.domain.mission.entity.Store;
import com.example.umc10th_proj.domain.mission.entity.mapping.MemberMission;
import com.example.umc10th_proj.domain.mission.enums.Address;
import com.example.umc10th_proj.domain.mission.exception.StoreException;
import com.example.umc10th_proj.domain.mission.exception.code.StoreErrorCode;
import com.example.umc10th_proj.domain.mission.repository.MemberMissionRepository;
import com.example.umc10th_proj.domain.mission.repository.MissionRepository;
import com.example.umc10th_proj.domain.mission.repository.StoreRepository;
import com.example.umc10th_proj.domain.mission.converter.MissionConverter;
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
    private final StoreRepository storeRepository;

    // 가게 미션 생성 (반환 타입 Void -> Mission으로 변경)
    @Transactional
    public Mission createMission(Long storeId, MissionReqDTO.CreateMission dto) {
        // 가게 찾기
        Store store = storeRepository.findById(storeId)
                .orElseThrow(() -> new StoreException(StoreErrorCode.NOT_FOUND));

        // 미션 생성
        Mission mission = MissionConverter.toMission(store, dto);

        // 미션 DB 저장
        missionRepository.save(mission);
        return null;
    }

    // 가게 내 미션들 조회
    public MissionResDTO.Pagination<MissionResDTO.GetMission> getMissions(
            Long storeId,
            Integer pageSize,
            Integer pageNumber,
            String sort
    ) {
        // 정렬 정보 생성
        Sort sortInfo;
        if (sort != null) {
            sortInfo = Sort.by(sort);
        } else {
            sortInfo = Sort.by("id").descending();
        }

        // 페이지 정보들을 PageRequest로 만들기
        PageRequest pageRequest = PageRequest.of(pageNumber, pageSize, sortInfo);

        // 가게 내 미션들 조회
        Page<Mission> missionList = missionRepository.findAllByStoreId(storeId, pageRequest);

        // 미션들 응답 DTO로 포장하기
        return MissionConverter.toPagination(
                missionList.map(MissionConverter::toGetMission).toList(),
                missionList.getNumber(),
                missionList.getSize()
        );
    }

    // 홈화면: 지역별 도전 가능한 미션 목록 (페이징)
    public Page<Mission> getHomeMissions(Address address, Integer page) {
        return missionRepository.findAvailableMissionsByAddress(
                address, PageRequest.of(page, 10)
        );
    }

    // 내 진행 중인 미션 목록 조회 (오프셋 페이징)
    public Page<MemberMission> getMyInProgressMissions(Long memberId, Integer page) {
        return memberMissionRepository.findInProgressMissionsByMemberId(
                memberId, PageRequest.of(page, 10)
        );
    }
}