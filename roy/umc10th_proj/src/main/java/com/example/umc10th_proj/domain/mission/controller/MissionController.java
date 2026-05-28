package com.example.umc10th_proj.domain.mission.controller;

import com.example.umc10th_proj.domain.mission.converter.MissionConverter;
import com.example.umc10th_proj.domain.mission.dto.MissionReqDTO;
import com.example.umc10th_proj.domain.mission.dto.MissionResDTO;
import com.example.umc10th_proj.domain.mission.entity.Mission;
import com.example.umc10th_proj.domain.mission.exception.code.MissionSuccessCode;
import com.example.umc10th_proj.domain.mission.service.MissionService;
import com.example.umc10th_proj.global.apiPayload.ApiResponse;
import com.example.umc10th_proj.global.apiPayload.code.BaseSuccessCode;
import com.example.umc10th_proj.global.apiPayload.code.GeneralSuccessCode;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1")
public class MissionController {

    private final MissionService missionService;

    // 1. 가게 미션 생성 (POST) - 사진과 동일하게 result: null 반환
    @PostMapping("/stores/{storeId}/missions")
    public ApiResponse<MissionResDTO.CreateMissionResult> createMission(
            @PathVariable(name = "storeId") Long storeId,
            @Valid @RequestBody MissionReqDTO.CreateMission request
    ) {

        Mission newMission = missionService.createMission(storeId, request);

        return ApiResponse.onSuccess(
                MissionSuccessCode.CREATED,
                MissionConverter.toCreateMissionResult(newMission)
        );
    }

    // 가게 내 미션들 조회 (오프셋 기반 페이징 - 커스텀 DTO 적용)
    @GetMapping("/stores/{storeId}/missions")
    public ApiResponse<MissionResDTO.Pagination<MissionResDTO.GetMission>> getMissions(
            @PathVariable(name = "storeId") Long storeId,
            @RequestParam(name = "pageSize") Integer pageSize,
            @RequestParam(name = "pageNumber") Integer pageNumber,
            @RequestParam(name = "sort", required = false) String sort
    ) {
        BaseSuccessCode code = MissionSuccessCode.OK;
        return ApiResponse.onSuccess(code, missionService.getMissions(storeId, pageSize, pageNumber, sort));
    }

    // 3. 미션 성공 누르기 (기존 코드 유지)
    @PatchMapping("/member-missions/{id}")
    public ApiResponse<String> completeMission(
            @PathVariable(name = "id") Long id,
            @RequestBody MissionReqDTO.CompleteMissionDto request
    ) {
        // TODO: missionService.completeMission(id, request) 호출 로직 작성
        return ApiResponse.onSuccess(GeneralSuccessCode.OK, "미션 성공 처리 완료");
    }
}