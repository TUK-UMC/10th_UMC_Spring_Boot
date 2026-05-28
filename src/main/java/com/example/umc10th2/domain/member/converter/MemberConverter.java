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

    // ✅ 로그인 응답 변환 (JWT 토큰 포함)
    public static MemberResDTO.LoginResponse toLoginResponse(String accessToken) {
        return MemberResDTO.LoginResponse.builder()
                .accessToken(accessToken)
                .tokenType("Bearer")
                .build();
    }

    // ✅ 마이페이지 응답 변환
    public static MemberResDTO.MyPageResponse toMyPageResponse(Member member) {
        return MemberResDTO.MyPageResponse.builder()
                .memberId(member.getId())
                .name(member.getName())
                .email(member.getEmail())
                .phoneNum(member.getPhoneNum())
                .address(member.getAddress())
                .birthDate(member.getBirthDate())
                .point(member.getPoint())
                .build();
    }
}
