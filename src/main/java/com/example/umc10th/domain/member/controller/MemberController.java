package com.example.umc10th.domain.member.controller;

import com.example.umc10th.domain.member.dto.MemberReqDTO;
import com.example.umc10th.domain.member.dto.MemberResDTO;
import com.example.umc10th.domain.member.exception.code.MemberSuccessCode;
import com.example.umc10th.domain.member.service.MemberService;
import com.example.umc10th.global.apiPayload.ApiResponse;
import com.example.umc10th.global.apiPayload.code.BaseSuccessCode;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
@Tag(name = "Member", description = "회원 관련 API")
public class MemberController {

    private final MemberService memberService;

    // 회원가입 - Public API (로그인 불필요)
    @Operation(summary = "회원가입", description = "이메일/비밀번호로 회원가입합니다. 비밀번호는 BCrypt 로 솔트 처리되어 저장됩니다.")
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/v1/auth/signup")
    public ApiResponse<MemberResDTO.SignUp> signUp(
            @RequestBody @Valid MemberReqDTO.SignUp dto
    ){
        BaseSuccessCode code = MemberSuccessCode.MEMBER_CREATED;
        return ApiResponse.onSuccess(code, memberService.signUp(dto));
    }

    // 마이페이지 - Private API (로그인 필요)
    @Operation(summary = "마이페이지 조회", description = "회원 정보를 조회합니다. (인증 필요)")
    @PostMapping("/v1/users/me")
    public ApiResponse<MemberResDTO.GetInfo> getInfo(
            @RequestBody @Valid MemberReqDTO.GetInfo dto
    ){
        BaseSuccessCode code = MemberSuccessCode.OK;
        return ApiResponse.onSuccess(code, memberService.getInfo(dto));
    }
}
