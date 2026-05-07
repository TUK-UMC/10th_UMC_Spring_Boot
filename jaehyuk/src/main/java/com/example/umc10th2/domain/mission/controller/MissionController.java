package com.example.umc10th2.domain.mission.controller;

import com.example.umc10th2.domain.mission.dto.MissionResDTO;
import com.example.umc10th2.domain.mission.service.MissionService;
import com.example.umc10th2.global.response.ApiResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@Tag(name = "Mission", description = "미션 관련 API")
@RestController
@RequestMapping("/missions")
@RequiredArgsConstructor
public class MissionController {

    private final MissionService missionService;

    @Operation(
        summary = "내 미션 목록 조회",
        description = "진행중(CHALLENGING) / 진행완료(COMPLETE) 미션을 페이징으로 조회합니다. status 미입력시 전체 조회."
    )
    @GetMapping("/members/{memberId}")
    public ApiResponse<MissionResDTO.MyMissionPageRes> getMyMissions(
            @PathVariable Long memberId,
            @RequestParam(required = false) String status,
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "10") int size
    ) {
        return ApiResponse.success(missionService.getMyMissions(memberId, status, page, size));
    }

    @Operation(
        summary = "홈 화면 - 지역별 도전 가능한 미션 목록",
        description = "선택된 지역(address)에서 아직 내가 도전하지 않은 미션을 페이징으로 조회합니다."
    )
    @GetMapping("/home")
    public ApiResponse<MissionResDTO.HomeMissionPageRes> getHomeMissions(
            @RequestParam Long memberId,
            @RequestParam String address,
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "10") int size
    ) {
        return ApiResponse.success(missionService.getHomeMissions(memberId, address, page, size));
    }

    @Operation(summary = "미션 완료 처리")
    @PatchMapping("/{missionId}/members/{memberId}/complete")
    public ApiResponse<?> completeMission(
            @PathVariable Long memberId,
            @PathVariable Long missionId
    ) {
        return ApiResponse.success(null);
    }
}
