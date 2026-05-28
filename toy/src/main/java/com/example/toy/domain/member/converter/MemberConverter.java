package com.example.toy.domain.member.converter;

import com.example.toy.domain.member.dto.MemberReqDTO;
import com.example.toy.domain.member.dto.MemberResDTO;
import com.example.toy.domain.member.entity.Member;

import java.util.ArrayList;

public class MemberConverter {

    public static MemberResDTO.GetInfo toGetInfo(Member member) {
        return MemberResDTO.GetInfo.builder()
                .email(member.getEmail())
                .name(member.getName())
                .point(member.getPoint())
                .phoneNumber(member.getPhoneNumber())
                .profileUrl(member.getProfileUrl())
                .build();
    }

    public static Member toMember(MemberReqDTO.SignUp dto, String encodedPassword) {
        return Member.builder()
                .name(dto.getName())
                .email(dto.getEmail())
                .password(encodedPassword)
                .gender(dto.getGender())
                .age(dto.getAge())
                .address(dto.getAddress())
                .specAddress(dto.getSpecAddress())
                .point(0)
                .memberTermList(new ArrayList<>())
                .memberFoodList(new ArrayList<>())
                .reviewList(new ArrayList<>())
                .memberMissionList(new ArrayList<>())
                .build();
    }

    public static MemberResDTO.SignUpResult toSignUpResult(Member member) {
        return MemberResDTO.SignUpResult.builder()
                .memberId(member.getId())
                .createdAt(member.getCreatedAt())
                .build();
    }
}
