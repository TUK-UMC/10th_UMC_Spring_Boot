package com.example.umc10th2.domain.mission.controller;

import com.example.umc10th2.domain.mission.dto.MissionReqDTO;
import com.example.umc10th2.domain.mission.dto.MissionResDTO;
import com.example.umc10th2.domain.mission.service.MissionService;
import com.example.umc10th2.global.response.ApiResponse;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/missions")
public class MissionController {

    private final MissionService missionService;

    // ✅ 내가 진행중인 미션 조회 (오프셋 페이징, memberId는 Request Body에서)
    @Operation(summary = "내 미션 목록 조회 (오프셋 페이지네이션)",
               description = "CHALLENGING / COMPLETE 상태로 필터링 가능. memberId는 Request Body로 전달.")
    @PostMapping("/my")
    public ApiResponse<MissionResDTO.MyMissionPageResponse> getMyMissions(
            @RequestBody @Valid MissionReqDTO.MyMissionRequest request) {
        return ApiResponse.success(missionService.getMyMissions(request));
    }
}
