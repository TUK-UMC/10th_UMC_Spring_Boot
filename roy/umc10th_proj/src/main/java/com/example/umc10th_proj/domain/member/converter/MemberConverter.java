package com.example.umc10th_proj.domain.member.converter;

import com.example.umc10th_proj.domain.member.dto.MemberResDTO;

public class MemberConverter {
    // 2. Converter 로직 (이전에 리뷰한 코드와 동일)
    // 마이페이지
    public static MemberResDTO.GetInfo toGetInfo(
            Member member
    ){
        return MemberResDTO.GetInfo.builder()
                .email(member.getEmail())
                .name(member.getName())
                .point(member.getPoint())
                .phoneNumber(member.getPhoneNumber())
                .profileUrl(member.getProfileUrl())
                .build();
    }
}