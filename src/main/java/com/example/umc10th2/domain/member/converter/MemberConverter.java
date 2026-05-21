package com.example.umc10th2.domain.member.converter;

import com.example.umc10th2.domain.member.dto.MemberReqDTO;
import com.example.umc10th2.domain.member.dto.MemberResDTO;
import com.example.umc10th2.domain.member.entity.Member;

public class MemberConverter {

    public static Member toMember(MemberReqDTO.SignupRequest request, String encodedPassword) {
        return Member.builder()
                .name(request.getName())
                .email(request.getEmail())
                .password(encodedPassword)
                .phoneNum(request.getPhoneNum())
                .gender(request.getGender())
                .birthDate(request.getBirthDate())
                .address(request.getAddress())
                .build();
    }

    public static MemberResDTO.SignupResponse toSignupResponse(Member member) {
        return MemberResDTO.SignupResponse.builder()
                .memberId(member.getId())
                .name(member.getName())
                .email(member.getEmail())
                .build();
    }
}
