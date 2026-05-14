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
import com.example.umc10th_proj.domain.review.converter.ReviewConverter;
import com.example.umc10th_proj.domain.review.dto.ReviewResDTO;
import com.example.umc10th_proj.domain.review.entity.Review;
import com.example.umc10th_proj.domain.review.service.ReviewService;
import com.example.umc10th_proj.global.apiPayload.ApiResponse;
import com.example.umc10th_proj.global.apiPayload.code.GeneralSuccessCode;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Slice;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/members")
public class MemberController {

    private final MemberService memberService;
    private final MissionService missionService;
    private final ReviewService reviewService;

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

    // 4. 내 진행 중인 미션 목록 (진행중, 페이징) - RequestBody 사용 및 하드코딩 제거
    @GetMapping("/me/missions")
    public ApiResponse<MissionResDTO.MyMissionListResult> getMyMissions(
            @RequestParam(defaultValue = "0") Integer page,
            @RequestBody MemberReqDTO.GetMyInProgressMissionsDto request
    ) {
        // 팩트: 서비스에 정의된 정확한 메서드명을 호출해야 하며, 하드코딩(1L) 대신 request.getMemberId()를 넘겨야 한다.
        Page<MemberMission> missions = missionService.getMyInProgressMissions(request.getMemberId(), page);
        return ApiResponse.onSuccess(
                GeneralSuccessCode.OK,
                MissionConverter.toMyMissionListResult(missions)
        );
    }

    // 5. 내가 작성한 리뷰 목록 (커서 페이징, 사진 제외)
    @GetMapping("/me/reviews")
    public ApiResponse<ReviewResDTO.MyReviewPreViewList> getMyReviews(
            @RequestParam(name = "memberId") Long memberId, // 실무에서는 반드시 토큰 기반 추출로 바꿔야 함
            @RequestParam(name = "sortType", defaultValue = "LATEST") String sortType,
            @RequestParam(name = "cursorId", required = false) Long cursorId,
            @RequestParam(name = "cursorStar", required = false) BigDecimal cursorStar,
            @RequestParam(name = "size", defaultValue = "10") Integer size
    ) {
        // 복합 커서 로직 호출
        Slice<Review> reviews = reviewService.getMyReviews(memberId, sortType, cursorId, cursorStar, size);
        return ApiResponse.onSuccess(
                GeneralSuccessCode.OK,
                ReviewConverter.toMyReviewPreViewList(reviews)
        );
    }
}