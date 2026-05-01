package com.example.umc10th_proj.domain.mission.controller;

import com.example.umc10th_proj.domain.mission.dto.MissionReqDTO;
import com.example.umc10th_proj.global.apiPayload.ApiResponse;
import com.example.umc10th_proj.global.apiPayload.code.GeneralSuccessCode;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1")
public class MissionController {

    // 미션 성공 누르기
    @PatchMapping("/member-missions/{id}")
    public ApiResponse<String> completeMission(
            @PathVariable(name = "id") Long id,
            @RequestBody MissionReqDTO.CompleteMissionDto request
    ) {
        // TODO: missionService.completeMission(id, request) 호출 로직 작성
        return ApiResponse.onSuccess(GeneralSuccessCode.OK, "미션 성공 처리 완료");
    }
}