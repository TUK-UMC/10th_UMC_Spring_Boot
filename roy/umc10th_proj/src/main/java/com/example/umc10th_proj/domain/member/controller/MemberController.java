package com.example.umc10th_proj.domain.member.controller;

import com.example.umc10th_proj.domain.member.dto.MemberReqDTO;
import com.example.umc10th_proj.domain.member.dto.MemberResDTO;
import com.example.umc10th_proj.domain.member.service.MemberService;
import com.example.umc10th_proj.domain.mission.converter.MissionConverter;
import com.example.umc10th_proj.domain.mission.dto.MissionResDTO;
import com.example.umc10th_proj.domain.mission.entity.Mission;
import com.example.umc10th_proj.domain.mission.entity.mapping.MemberMission;
import com.example.umc10th_proj.domain.mission.enums.Address;
import com.example.umc10th_proj.domain.mission.service.MissionService;
import com.example.umc10th_proj.global.apiPayload.ApiResponse;
import com.example.umc10th_proj.global.apiPayload.code.GeneralSuccessCode;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/members")
public class MemberController {

    private final MemberService memberService;
    private final MissionService missionService;

    // 1. 회원 가입
    @PostMapping
    public ApiResponse<String> join(@RequestBody MemberReqDTO.JoinDto request) {
        return ApiResponse.onSuccess(GeneralSuccessCode.OK, "회원가입 성공");
    }

    // 2. 마이페이지 화면
    @GetMapping("/me")
    public ApiResponse<MemberResDTO.GetInfo> getMyPage() {
        // 임시로 id=1L, 실제로는 JWT에서 추출
        MemberResDTO.GetInfo result = memberService.getInfo(
                MemberReqDTO.GetInfo.builder().id(1L).build()
        );
        return ApiResponse.onSuccess(GeneralSuccessCode.OK, result);
    }

    // 3. 홈화면 — 현재 지역의 도전 가능한 미션 목록 (페이징)
    @GetMapping("/me/home-summary")
    public ApiResponse<MissionResDTO.HomeMissionListResult> getHomeSummary(
            @RequestParam(defaultValue = "0") Integer page,
            @RequestParam Address address
    ) {
        Page<Mission> missions = missionService.getHomeMissions(address, page);
        return ApiResponse.onSuccess(
                GeneralSuccessCode.OK,
                MissionConverter.toHomeMissionListResult(missions)
        );
    }

    // 4. 내 미션 목록 (진행중 + 완료, 페이징)
    @GetMapping("/me/missions")
    public ApiResponse<MissionResDTO.MyMissionListResult> getMyMissions(
            @RequestParam(defaultValue = "0") Integer page
    ) {
        // 임시로 memberId=1L, 실제로는 JWT에서 추출
        Page<MemberMission> missions = missionService.getMyMissions(1L, page);
        return ApiResponse.onSuccess(
                GeneralSuccessCode.OK,
                MissionConverter.toMyMissionListResult(missions)
        );
    }
}