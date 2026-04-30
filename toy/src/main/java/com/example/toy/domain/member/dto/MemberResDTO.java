package com.example.toy.domain.member.dto;

import lombok.Builder;
import lombok.Getter;

public class MemberResDTO {

    @Builder
    public record GetInfo(
            String name,
            String profileUrl,
            String email,
            String phoneNumber,
            Integer point
    ){}
}
