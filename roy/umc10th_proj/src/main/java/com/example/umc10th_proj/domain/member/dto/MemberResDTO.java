package com.example.umc10th_proj.domain.member.dto;

import lombok.Builder;

import java.time.LocalDateTime;

public class MemberResDTO {

    @Builder
    public record GetInfo(
            String name,
            String profileUrl,
            String email,
            String phoneNumber,
            Integer point
    ) {}

    @Builder
    public record JoinResult(
            Long memberId,
            LocalDateTime createdAt
    ) {}

    @Builder
    public record Login(
            String accessToken
    ) {}
}