package com.example.umc10th.domain.member.converter;

import com.example.umc10th.domain.member.dto.MemberReqDTO;
import com.example.umc10th.domain.member.dto.MemberResDTO;
import com.example.umc10th.domain.member.entity.Member;

public class MemberConverter {
    public static MemberResDTO.GetInfo toGetInfo(Member member){
        return MemberResDTO.GetInfo.builder()
                .email(member.getEmail())
                .name(member.getName())
                .point(member.getPoint())
                .phoneNumber(member.getPhoneNumber())
                .profileUrl(member.getProfileUrl())
                .build();
    }

    // 회원가입 요청 DTO + 암호화된 비밀번호 -> Member 엔티티
    public static Member toMember(MemberReqDTO.SignUp dto, String encodedPassword){
        return Member.builder()
                .name(dto.name())
                .email(dto.email())
                .password(encodedPassword)
                .build();
    }

    // 저장된 Member -> 회원가입 응답 DTO
    public static MemberResDTO.SignUp toSignUpResult(Member member){
        return MemberResDTO.SignUp.builder()
                .memberId(member.getId())
                .name(member.getName())
                .email(member.getEmail())
                .createdAt(member.getCreatedAt())
                .build();
    }
}