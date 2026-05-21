package com.example.umc10th2.domain.member.service;

import com.example.umc10th2.domain.member.converter.MemberConverter;
import com.example.umc10th2.domain.member.dto.MemberReqDTO;
import com.example.umc10th2.domain.member.dto.MemberResDTO;
import com.example.umc10th2.domain.member.entity.Member;
import com.example.umc10th2.domain.member.exception.MemberException;
import com.example.umc10th2.domain.member.exception.code.MemberErrorCode;
import com.example.umc10th2.domain.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MemberService {

    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;

    @Transactional
    public MemberResDTO.SignupResponse signup(MemberReqDTO.SignupRequest request) {
        if (memberRepository.existsByEmail(request.getEmail())) {
            throw new MemberException(MemberErrorCode.DUPLICATE_EMAIL);
        }
        String encodedPassword = passwordEncoder.encode(request.getPassword());
        Member member = MemberConverter.toMember(request, encodedPassword);
        return MemberConverter.toSignupResponse(memberRepository.save(member));
    }

    public Member getMember(Long memberId) {
        return memberRepository.findById(memberId)
                .orElseThrow(() -> new MemberException(MemberErrorCode.MEMBER_NOT_FOUND));
    }
}
