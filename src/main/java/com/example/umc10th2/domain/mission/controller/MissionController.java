package com.example.umc10th2.domain.mission.controller;

import com.example.umc10th2.global.response.ApiResponse;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/members")
public class MissionController {

    @GetMapping("/{memberId}/missions")
    public ApiResponse<?> getMissions(
            @PathVariable Long memberId,
            @RequestParam(required = false) String status,
            @RequestParam int page,
            @RequestParam int size
    ) {
        return ApiResponse.success(null);
    }

    @PatchMapping("/{memberId}/missions/{missionId}")
    public ApiResponse<?> completeMission(
            @PathVariable Long memberId,
            @PathVariable Long missionId
    ) {
        return ApiResponse.success(null);
    }
}