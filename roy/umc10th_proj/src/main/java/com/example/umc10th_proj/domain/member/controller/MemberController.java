package com.example.umc10th_proj.domain.member.controller;

import org.springframework.web.bind.annotation.*;
import lombok.RequiredArgsConstructor;
import com.example.umc10th_proj.global.apiPayload.ApiResponse;
import com.example.umc10th_proj.domain.member.dto.MemberResDTO;
import com.example.umc10th_proj.domain.member.service.MemberService;
import com.example.umc10th_proj.domain.member.exception.code.MemberSuccessCode;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class MemberController {

    private final MemberService memberService;

    @GetMapping("/v1/users/me")
    public ApiResponse<MemberResDTO.GetInfo> getInfo() {
        // TODO: 추후 Spring Security의 SecurityContextHolder에서 추출한 유저 ID로 교체
        Long currentUserId = 1L;

        return ApiResponse.onSuccess(MemberSuccessCode.OK, memberService.getInfo(currentUserId));
    }
}