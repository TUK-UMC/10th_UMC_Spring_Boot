package com.example.umc10th_proj.global.security.service;

import com.example.umc10th_proj.domain.member.entity.Member;
import com.example.umc10th_proj.domain.member.enums.SocialType;
import com.example.umc10th_proj.domain.member.exception.MemberException;
import com.example.umc10th_proj.domain.member.exception.code.MemberErrorCode;
import com.example.umc10th_proj.domain.member.repository.MemberRepository;
import com.example.umc10th_proj.global.security.entity.AuthMember;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

    private final MemberRepository memberRepository;

    // UserDetailsService 필수 구현 메서드 (사용하지 않지만 오버라이드 필요)
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Member member = memberRepository.findByEmail(email)
                .orElseThrow(() -> new MemberException(MemberErrorCode.MEMBER_NOT_FOUND));
        return new AuthMember(member);
    }

    /**
     * JWT 필터에서 사용하는 조회 메서드
     * socialType + socialUid 조합으로 회원을 찾는다.
     * - 일반 회원: socialType=LOCAL, socialUid=email
     * - OAuth 회원: socialType=KAKAO 등, socialUid=provider uid
     */
    public UserDetails loadUserByUidAndSocialType(SocialType socialType, String uid)
            throws UsernameNotFoundException {
        Member member = memberRepository.findBySocialTypeAndSocialUid(socialType, uid)
                .orElseThrow(() -> new MemberException(MemberErrorCode.MEMBER_NOT_FOUND));
        return new AuthMember(member);
    }
}