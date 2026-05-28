package com.example.toy.domain.member.controller;

import com.example.toy.domain.member.dto.MemberReqDTO;
import com.example.toy.domain.member.dto.MemberResDTO;
import com.example.toy.domain.member.exception.code.MemberSuccessCode;
import com.example.toy.domain.member.service.MemberService;
import com.example.toy.global.apiPayload.ApiResponse;
import com.example.toy.global.apiPayload.code.BaseSuccessCode;
import com.example.toy.global.entity.AuthMember;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class MemberController {

    private final MemberService memberService;

    @GetMapping("/v2/users/me")
    public ApiResponse<MemberResDTO.GetInfo> getInfo(@AuthenticationPrincipal AuthMember member){
        BaseSuccessCode code = MemberSuccessCode.OK;
        return ApiResponse.onSuccess(code, memberService.getInfo(member));
    }

    @GetMapping("/v1/members/{memberId}")
    public ApiResponse<MemberResDTO.GetInfo> getMyPageProfile(@PathVariable Long memberId) {
        return ApiResponse.onSuccess(MemberSuccessCode.OK, memberService.getProfile(memberId));
    }

    @PostMapping("/v1/members/signup")
    public ApiResponse<MemberResDTO.SignUpResult> signUp(@RequestBody @Valid MemberReqDTO.SignUp dto) {
        return ApiResponse.onSuccess(MemberSuccessCode.CREATED, memberService.signUp(dto));
    }

    @PostMapping("/v1/members/login")
    public ApiResponse<MemberResDTO.LoginResult> login(@RequestBody @Valid MemberReqDTO.Login dto) {
        return ApiResponse.onSuccess(MemberSuccessCode.OK, memberService.login(dto));
    }
}
