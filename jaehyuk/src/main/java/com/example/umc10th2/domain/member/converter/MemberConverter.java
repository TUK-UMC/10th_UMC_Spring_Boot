package com.example.umc10th2.domain.member.converter;

import com.example.umc10th2.domain.member.dto.MemberResDTO;
import com.example.umc10th2.domain.member.entity.Member;

public class MemberConverter {

    public static MemberResDTO.MyPageRes toMyPageRes(Member member) {
        return MemberResDTO.MyPageRes.builder()
                .nickname(member.getNickname())
                .email(member.getEmail())
                .phoneNum(member.getPhoneNum())
                .point(member.getPoint())
                .build();
    }
}
