package com.example.umc10th2.domain.member.service;

import com.example.umc10th2.domain.member.converter.MemberConverter;
import com.example.umc10th2.domain.member.dto.MemberReqDTO;
import com.example.umc10th2.domain.member.dto.MemberResDTO;
import com.example.umc10th2.domain.member.entity.Member;
import com.example.umc10th2.domain.member.exception.MemberException;
import com.example.umc10th2.domain.member.exception.code.MemberErrorCode;
import com.example.umc10th2.domain.member.repository.MemberRepository;
import com.example.umc10th2.global.security.AuthMember;
import com.example.umc10th2.global.security.JwtUtil;
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
    private final JwtUtil jwtUtil;

    // ✅ 회원가입
    @Transactional
    public MemberResDTO.SignupResponse signup(MemberReqDTO.SignupRequest request) {
        if (memberRepository.existsByEmail(request.getEmail())) {
            throw new MemberException(MemberErrorCode.DUPLICATE_EMAIL);
        }
        String encodedPassword = passwordEncoder.encode(request.getPassword());
        Member member = MemberConverter.toMember(request, encodedPassword);
        return MemberConverter.toSignupResponse(memberRepository.save(member));
    }

    // ✅ 로그인 - 비밀번호 검증 후 JWT 발급
    public MemberResDTO.LoginResponse login(MemberReqDTO.LoginRequest request) {
        Member member = memberRepository.findByEmailAndDeletedAtIsNull(request.getEmail())
                .orElseThrow(() -> new MemberException(MemberErrorCode.MEMBER_NOT_FOUND));

        if (!passwordEncoder.matches(request.getPassword(), member.getPassword())) {
            throw new MemberException(MemberErrorCode.INVALID_PASSWORD);
        }

        String accessToken = jwtUtil.createAccessToken(new AuthMember(member));
        return MemberConverter.toLoginResponse(accessToken);
    }

    // ✅ 마이페이지 - 토큰에서 추출한 AuthMember로 조회
    public MemberResDTO.MyPageResponse getMyPage(AuthMember authMember) {
        return MemberConverter.toMyPageResponse(authMember.getMember());
    }

    public Member getMember(Long memberId) {
        return memberRepository.findById(memberId)
                .orElseThrow(() -> new MemberException(MemberErrorCode.MEMBER_NOT_FOUND));
    }
}
