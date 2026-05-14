package com.example.toy.domain.mission.controller;

import com.example.toy.domain.mission.dto.MissionResDTO;
import com.example.toy.domain.mission.entity.Mission;
import com.example.toy.domain.mission.service.MissionService;
import com.example.toy.global.apiPayload.ApiResponse;
import com.example.toy.global.apiPayload.code.BaseSuccessCode;
import com.example.toy.global.apiPayload.code.GeneralSuccessCode;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/missions")
public class MissionController {
    private final MissionService missionService;

    @GetMapping("/region/{regionId}")
    public ApiResponse<Page<Mission>> getRegionMissions(@PathVariable Long regionId, @RequestParam Integer page) {
        BaseSuccessCode code = GeneralSuccessCode.OK;
        return ApiResponse.onSuccess(code, missionService.getRegionMissionList(regionId, page));
    }

    @GetMapping("/members/{memberId}")
    public ApiResponse<MissionResDTO.Pagination<MissionResDTO.MyMissionDTO>> getMyMissions(@PathVariable Long memberId,
                                                          @RequestParam String status,
                                                          @RequestParam Integer pageSize,
                                                          @RequestParam Integer pageNumber,
                                                          @RequestParam(required = false) String sort ) {
        BaseSuccessCode code = GeneralSuccessCode.OK;
        return ApiResponse.onSuccess(code, missionService.getMyMissionList(memberId, status, pageSize, pageNumber, sort));
    }

    @GetMapping("/regions/{regionName}/available")
    public ApiResponse<Page<MissionResDTO.HomeMissionDTO>> getAvailableMissions(
            @PathVariable String regionName,
            @RequestParam Long memberId,
            @RequestParam Integer page) {
        BaseSuccessCode code = GeneralSuccessCode.OK;
        return ApiResponse.onSuccess(code, missionService.getAvailableMissionsByRegion(regionName, memberId, page));
    }
}
