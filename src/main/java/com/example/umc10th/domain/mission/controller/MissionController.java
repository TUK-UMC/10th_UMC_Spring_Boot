package com.example.umc10th.domain.mission.controller;

import com.example.umc10th.domain.mission.dto.MissionReqDTO;
import com.example.umc10th.domain.mission.dto.MissionResDTO;
import com.example.umc10th.domain.mission.enums.MissionStatus;
import com.example.umc10th.domain.mission.exception.code.MissionSuccessCode;
import com.example.umc10th.domain.mission.service.MissionService;
import com.example.umc10th.global.apiPayload.ApiResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/members/me/missions")
public class MissionController {

    private final MissionService missionService;

    /**
     * 내가 진행중인 미션 조회 (오프셋 기반 페이지네이션)
     * - 사용자 ID는 Request Body 로 받음 (하드코딩 X)
     * - status, page, size 는 쿼리 파라미터로 받음
     */
    @GetMapping
    public ApiResponse<MissionResDTO.MyMissionPreViewListDTO> getMyMissions(
            @RequestBody @Valid MissionReqDTO.GetMyMissions request,
            @RequestParam(defaultValue = "ONGOING") MissionStatus status,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
    ) {
        MissionResDTO.MyMissionPreViewListDTO result =
                missionService.getMyMissions(request.memberId(), status, page, size);

        return ApiResponse.onSuccess(MissionSuccessCode.OK, result);
    }
}
