package com.example.umc10th2.domain.member.controller;

import com.example.umc10th2.domain.member.dto.MemberReqDTO;
import com.example.umc10th2.domain.member.dto.MemberResDTO;
import com.example.umc10th2.domain.member.service.MemberService;
import com.example.umc10th2.global.response.ApiResponse;
import com.example.umc10th2.global.security.AuthMember;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@Tag(name = "Member", description = "회원 관련 API")
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/members")
public class MemberController {

    private final MemberService memberService;

    // ✅ Public - 회원가입
    @Operation(summary = "회원가입 (Public)")
    @PostMapping("/signup")
    public ApiResponse<MemberResDTO.SignupResponse> signup(
            @RequestBody @Valid MemberReqDTO.SignupRequest request) {
        return ApiResponse.success(memberService.signup(request));
    }

    // ✅ Public - 로그인 (JWT 발급)
    @Operation(summary = "로그인 (Public) - JWT 토큰 발급")
    @PostMapping("/login")
    public ApiResponse<MemberResDTO.LoginResponse> login(
            @RequestBody @Valid MemberReqDTO.LoginRequest request) {
        return ApiResponse.success(memberService.login(request));
    }

    // ✅ Private - 마이페이지 (@AuthenticationPrincipal로 토큰에서 사용자 추출)
    @Operation(summary = "마이페이지 (Private) - JWT 토큰 필요",
               security = @SecurityRequirement(name = "bearerAuth"))
    @GetMapping("/my")
    public ApiResponse<MemberResDTO.MyPageResponse> getMyPage(
            @AuthenticationPrincipal AuthMember authMember) {
        return ApiResponse.success(memberService.getMyPage(authMember));
    }
}
