package com.example.umc10th2.domain.member.controller;

import com.example.umc10th2.domain.member.dto.MemberReqDTO;
import com.example.umc10th2.domain.member.dto.MemberResDTO;
import com.example.umc10th2.domain.member.service.MemberService;
import com.example.umc10th2.global.response.ApiResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@Tag(name = "Member", description = "회원 관련 API")
@RestController
@RequestMapping("/members")
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;

    @Operation(summary = "회원가입")
    @PostMapping("/signup")
    public ApiResponse<?> signup(@RequestBody MemberReqDTO request) {
        return ApiResponse.success(null);
    }

    @Operation(summary = "로그인")
    @PostMapping("/login")
    public ApiResponse<?> login(@RequestBody MemberResDTO request) {
        return ApiResponse.success(null);
    }

    @Operation(summary = "마이페이지 조회", description = "닉네임, 이메일, 포인트 등 내 정보를 조회합니다.")
    @GetMapping("/{memberId}")
    public ApiResponse<MemberResDTO.MyPageRes> getMyInfo(@PathVariable Long memberId) {
        return ApiResponse.success(memberService.getMyPage(memberId));
    }

    @Operation(summary = "포인트 내역 조회")
    @GetMapping("/{memberId}/points")
    public ApiResponse<?> getPoints(
            @PathVariable Long memberId,
            @RequestParam int page,
            @RequestParam int size
    ) {
        return ApiResponse.success(null);
    }
}
