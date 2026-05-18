package com.example.umc10th_proj.domain.member.converter;

import com.example.umc10th_proj.domain.member.dto.MemberReqDTO;
import com.example.umc10th_proj.domain.member.dto.MemberResDTO;
import com.example.umc10th_proj.domain.member.entity.Member;
import com.example.umc10th_proj.domain.member.enums.Gender;
import com.example.umc10th_proj.domain.mission.enums.Address;

import java.time.LocalDate;
import java.util.ArrayList;

public class MemberConverter {
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

     // DTO -> Entity 변환 (비밀번호는 Service에서 해싱된 값을 넘겨받음)
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