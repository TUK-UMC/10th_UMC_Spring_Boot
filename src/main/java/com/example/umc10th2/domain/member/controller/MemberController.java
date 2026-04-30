package com.example.umc10th2.domain.member.controller;

import com.example.umc10th2.domain.member.dto.MemberReqDTO;
import com.example.umc10th2.domain.member.dto.MemberResDTO;
import com.example.umc10th2.global.response.ApiResponse;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/members")
public class MemberController {

    @PostMapping("/signup")
    public ApiResponse<?> signup(@RequestBody MemberReqDTO request) {
        return ApiResponse.success(null);
    }

    @PostMapping("/login")
    public ApiResponse<?> login(@RequestBody MemberResDTO request) {
        return ApiResponse.success(null);
    }

    @GetMapping("/{memberId}")
    public ApiResponse<?> getMyInfo(@PathVariable Long memberId) {
        return ApiResponse.success(null);
    }

    @GetMapping("/{memberId}/points")
    public ApiResponse<?> getPoints(
            @PathVariable Long memberId,
            @RequestParam int page,
            @RequestParam int size
    ) {
        return ApiResponse.success(null);
    }
}