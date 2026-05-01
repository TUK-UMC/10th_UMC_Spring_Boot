package com.example.umc10th_proj.domain.member.converter;

import com.example.umc10th_proj.domain.member.dto.MemberResDTO;
import com.example.umc10th_proj.domain.member.entity.Member;

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
}

//public class MemberConverter{
//
//    public static MemberResDTO.RequestBody toRequestBody(
//            String stringTest,
//            Long longTest
//    ){
//        return MemberResDTO.RequestBody.builder()
//                .stringTest(stringTest)
//                .longTest(longTest)
//                .build();
//    }
//}