package com.example.umc10th_proj.domain.member.service;

import com.example.umc10th_proj.domain.member.dto.MemberReqDTO;
import com.example.umc10th_proj.domain.member.dto.MemberResDTO;
import com.example.umc10th_proj.domain.member.entity.Member;
import com.example.umc10th_proj.domain.member.exception.MemberException;
import com.example.umc10th_proj.domain.member.exception.code.MemberErrorCode;
import com.example.umc10th_proj.domain.member.repository.MemberRepository;
import com.example.umc10th_proj.domain.member.converter.MemberConverter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MemberService {
    private final MemberRepository memberRepository;

    public MemberResDTO.GetInfo getInfo(MemberReqDTO.GetInfo dto) {
        Member member = memberRepository.findByNameAndDeletedAtIsNull("마크")
                .orElseThrow(() -> new MemberException(MemberErrorCode.MEMBER_NOT_FOUND));
        return MemberConverter.toGetInfo(member);
    }
}