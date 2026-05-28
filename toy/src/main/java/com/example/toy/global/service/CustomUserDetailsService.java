package com.example.toy.global.service;


import com.example.toy.domain.member.entity.Member;
import com.example.toy.domain.member.exception.MemberException;
import com.example.toy.domain.member.exception.code.MemberErrorCode;
import com.example.toy.domain.member.repository.MemberRepository;
import com.example.toy.global.entity.AuthMember;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

    private final MemberRepository memberRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Member member = memberRepository.findByEmail(username)
                .orElseThrow(() -> new MemberException((MemberErrorCode.MEMBER_NOT_FOUND)));
        return new AuthMember(member);
    }
}
