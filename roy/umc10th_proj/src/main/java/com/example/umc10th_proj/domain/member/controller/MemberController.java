package com.example.umc10th_proj.domain.member.controller;

import com.example.umc10th_proj.domain.member.converter.MemberConverter;
import com.example.umc10th_proj.domain.member.dto.MemberReqDTO;
import com.example.umc10th_proj.domain.member.dto.MemberResDTO;
import com.example.umc10th_proj.domain.member.entity.Member;
import com.example.umc10th_proj.domain.member.exception.code.MemberSuccessCode;
import com.example.umc10th_proj.domain.member.service.MemberService;
import com.example.umc10th_proj.domain.mission.converter.MissionConverter;
import com.example.umc10th_proj.domain.mission.dto.MissionResDTO;
import com.example.umc10th_proj.domain.mission.entity.mapping.MemberMission;
import com.example.umc10th_proj.domain.mission.enums.Address;
import com.example.umc10th_proj.domain.mission.service.MissionService;
import com.example.umc10th_proj.domain.review.converter.ReviewConverter;
import com.example.umc10th_proj.domain.review.dto.ReviewResDTO;
import com.example.umc10th_proj.domain.review.entity.Review;
import com.example.umc10th_proj.domain.review.service.ReviewService;
import com.example.umc10th_proj.global.apiPayload.ApiResponse;
import com.example.umc10th_proj.global.apiPayload.code.GeneralSuccessCode;
import com.example.umc10th_proj.global.security.entity.AuthMember;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Slice;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;

@RestController
@RequiredArgsConstructor
@Validated
@RequestMapping("/api/v1/members")
public class MemberController {

    private final MemberService memberService;
    private final MissionService missionService;
    private final ReviewService reviewService;

    // 1. 회원가입
    @PostMapping("/signup")
    public ApiResponse<MemberResDTO.JoinResult> join(@RequestBody @Valid MemberReqDTO.JoinDto request) {
        Member newMember = memberService.join(request);
        return ApiResponse.onSuccess(
                GeneralSuccessCode.OK,
                MemberConverter.toJoinResult(newMember)
        );
    }

    // 2. 로그인
    @PostMapping("/login")
    public ApiResponse<MemberResDTO.Login> login(@RequestBody @Valid MemberReqDTO.LoginDto request) {
        return ApiResponse.onSuccess(
                MemberSuccessCode.LOGIN_OK,
                memberService.login(request)
        );
    }

    // 3. 마이페이지 (@AuthenticationPrincipal 로 개선)
    @GetMapping("/me")
    public ApiResponse<MemberResDTO.GetInfo> getMyPage(
            @AuthenticationPrincipal AuthMember authMember
    ) {
        return ApiResponse.onSuccess(
                MemberSuccessCode.OK,
                memberService.getInfo(authMember.getMember().getId())
        );
    }

    // 4. 홈화면 — 현재 지역의 도전 가능한 미션 목록 (페이징)
    @GetMapping("/me/home-summary")
    public ApiResponse<MissionResDTO.HomeMissionListResult> getHomeSummary(
            @RequestParam(defaultValue = "0") Integer page,
            @RequestParam Address address
    ) {
        return ApiResponse.onSuccess(
                GeneralSuccessCode.OK,
                MissionConverter.toHomeMissionListResult(
                        missionService.getHomeMissions(address, page)
                )
        );
    }

    // 5. 내 진행 중인 미션 목록 (오프셋 페이징)
    // 7주차 미션: 사용자 ID는 Request Body에서 받기
    @PostMapping("/me/missions")
    public ApiResponse<MissionResDTO.MyMissionListResult> getMyMissions(
            @RequestParam(defaultValue = "0") Integer page,
            @RequestBody @Valid MemberReqDTO.GetMyInProgressMissionsDto request
    ) {
        Page<MemberMission> missions = missionService.getMyInProgressMissions(request.getMemberId(), page);
        return ApiResponse.onSuccess(
                GeneralSuccessCode.OK,
                MissionConverter.toMyMissionListResult(missions)
        );
    }

    // 6. 내가 작성한 리뷰 목록 (커서 페이징)
    @GetMapping("/me/reviews")
    public ApiResponse<ReviewResDTO.MyReviewPreViewList> getMyReviews(
            @RequestParam Long memberId,
            @RequestParam(defaultValue = "LATEST") String sortType,
            @RequestParam(required = false) Long cursorId,
            @RequestParam(required = false) BigDecimal cursorStar,
            @RequestParam(defaultValue = "10") Integer size
    ) {
        Slice<Review> reviews = reviewService.getMyReviews(memberId, sortType, cursorId, cursorStar, size);
        return ApiResponse.onSuccess(
                GeneralSuccessCode.OK,
                ReviewConverter.toMyReviewPreViewList(reviews)
        );
    }
}