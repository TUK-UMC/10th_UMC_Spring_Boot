package com.example.umc10th.domain.member.service;

import com.example.umc10th.domain.member.converter.MemberConverter;
import com.example.umc10th.domain.member.dto.MemberReqDTO;
import com.example.umc10th.domain.member.dto.MemberResDTO;
import com.example.umc10th.domain.member.exception.MemberException;
import com.example.umc10th.domain.member.exception.code.MemberErrorCode;
import com.example.umc10th.domain.member.repository.MemberRepository;
import com.example.umc10th.domain.member.entity.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class MemberService {
    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;

    @Transactional(readOnly = true)
    public MemberResDTO.GetInfo getInfo(MemberReqDTO.GetInfo dto){
        Long memberId = dto.id();
        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new MemberException(MemberErrorCode.MEMBER_NOT_FOUND));
        return MemberConverter.toGetInfo(member);
    }

    /**
     * 회원가입 (폼 로그인용)
     * - 이메일 중복 검증
     * - 비밀번호는 BCrypt 로 솔트 처리하여 저장 (평문 저장 금지)
     */
    @Transactional
    public MemberResDTO.SignUp signUp(MemberReqDTO.SignUp dto){
        // 이메일 중복 검증
        if (memberRepository.existsByEmail(dto.email())) {
            throw new MemberException(MemberErrorCode.MEMBER_EMAIL_DUPLICATE);
        }

        // BCrypt 솔트 처리 (PasswordEncoder.encode 가 솔트를 자동 생성/포함)
        String encodedPassword = passwordEncoder.encode(dto.password());

        Member member = MemberConverter.toMember(dto, encodedPassword);
        Member savedMember = memberRepository.save(member);

        return MemberConverter.toSignUpResult(savedMember);
    }
}
