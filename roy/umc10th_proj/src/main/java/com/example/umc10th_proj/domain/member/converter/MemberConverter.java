package com.example.umc10th_proj.domain.member.converter;

import com.example.umc10th_proj.domain.member.dto.MemberReqDTO;
import com.example.umc10th_proj.domain.member.dto.MemberResDTO;
import com.example.umc10th_proj.domain.member.entity.Member;
import com.example.umc10th_proj.domain.member.enums.Gender;
import com.example.umc10th_proj.domain.member.enums.SocialType;
import com.example.umc10th_proj.domain.mission.enums.Address;
import com.example.umc10th_proj.global.security.dto.OAuthDTO;

import java.time.LocalDate;
import java.util.ArrayList;

public class MemberConverter {

    // 마이페이지
    public static MemberResDTO.GetInfo toGetInfo(Member member) {
        return MemberResDTO.GetInfo.builder()
                .email(member.getEmail())
                .name(member.getName())
                .point(member.getPoint())
                .phoneNumber(member.getPhoneNumber())
                .profileUrl(member.getProfileUrl())
                .build();
    }

    // 로그인 응답
    public static MemberResDTO.Login toLogin(String accessToken) {
        return MemberResDTO.Login.builder()
                .accessToken(accessToken)
                .build();
    }

    // 일반 회원가입 DTO -> Entity 변환
    // socialUid = email, socialType = LOCAL 로 설정해 JWT 필터와 일관된 조회가 가능하도록 함
    public static Member toMember(MemberReqDTO.JoinDto request, String encodedPassword) {
        Gender gender = switch (request.getGender()) {
            case 1 -> Gender.MALE;
            case 2 -> Gender.FEMALE;
            case 3 -> Gender.NONE;
            default -> Gender.NONE;
        };

        return Member.builder()
                .email(request.getEmail())
                .password(encodedPassword)
                .name(request.getName())
                .gender(gender)
                .birth(LocalDate.of(request.getBirthYear(), request.getBirthMonth(), request.getBirthDay()))
                .address(Address.valueOf(request.getAddress()))
                .detailAddress(request.getDetailAddress())
                // 일반 회원: email을 UID로 사용하고 socialType = LOCAL
                .socialUid(request.getEmail())
                .socialType(SocialType.LOCAL)
                .memberFoodList(new ArrayList<>())
                .memberTermList(new ArrayList<>())
                .build();
    }

    // OAuth 회원가입 DTO -> Entity 변환
    public static Member toMember(OAuthDTO dto) {
        return Member.builder()
                .email(dto.getSocialEmail())
                .name(dto.getName())
                .socialType(dto.getSocialType())
                .socialUid(dto.getSocialUid())
                // OAuth 회원은 비밀번호 없음 → 빈 문자열로 저장 (null 방지)
                .password("")
                .memberFoodList(new ArrayList<>())
                .memberTermList(new ArrayList<>())
                .build();
    }

    // Entity -> 응답 DTO 변환
    public static MemberResDTO.JoinResult toJoinResult(Member member) {
        return MemberResDTO.JoinResult.builder()
                .memberId(member.getId())
                .createdAt(member.getCreatedAt())
                .build();
    }
}