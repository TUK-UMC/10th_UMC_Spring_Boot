package com.example.umc10th2.domain.member.controller;

import com.example.umc10th2.domain.member.dto.MemberReqDTO;
import com.example.umc10th2.domain.member.dto.MemberResDTO;
import com.example.umc10th2.domain.member.service.MemberService;
import com.example.umc10th2.global.response.ApiResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@Tag(name = "Member", description = "회원 관련 API")
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/members")
public class MemberController {

    private final MemberService memberService;

    @Operation(summary = "회원가입")
    @PostMapping("/signup")
    public ApiResponse<MemberResDTO.SignupResponse> signup(
            @RequestBody @Valid MemberReqDTO.SignupRequest request) {
        return ApiResponse.success(memberService.signup(request));
    }

    @Operation(summary = "내 정보 조회")
    @GetMapping("/{memberId}")
    public ApiResponse<?> getMyInfo(@PathVariable Long memberId) {
        return ApiResponse.success(memberService.getMember(memberId));
    }

}
