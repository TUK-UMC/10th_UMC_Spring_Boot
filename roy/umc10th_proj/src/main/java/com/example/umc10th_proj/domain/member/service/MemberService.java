package com.example.umc10th_proj.domain.member.service;

import com.example.umc10th_proj.domain.member.converter.MemberConverter;
import com.example.umc10th_proj.domain.member.dto.MemberReqDTO;
import com.example.umc10th_proj.domain.member.dto.MemberResDTO;
import com.example.umc10th_proj.domain.member.entity.Food;
import com.example.umc10th_proj.domain.member.entity.Member;
import com.example.umc10th_proj.domain.member.entity.Term;
import com.example.umc10th_proj.domain.member.entity.mapping.MemberFood;
import com.example.umc10th_proj.domain.member.entity.mapping.MemberTerm;
import com.example.umc10th_proj.domain.member.exception.MemberException;
import com.example.umc10th_proj.domain.member.exception.code.MemberErrorCode;
import com.example.umc10th_proj.domain.member.repository.FoodRepository;
import com.example.umc10th_proj.domain.member.repository.MemberRepository;
import com.example.umc10th_proj.domain.member.repository.TermRepository;
import com.example.umc10th_proj.global.security.entity.AuthMember;
import com.example.umc10th_proj.global.security.util.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;
    private final FoodRepository foodRepository;
    private final TermRepository termRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;

    // 마이페이지 조회
    public MemberResDTO.GetInfo getInfo(Long memberId) {
        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new MemberException(MemberErrorCode.MEMBER_NOT_FOUND));
        return MemberConverter.toGetInfo(member);
    }

    // 로그인
    // email로 일반(LOCAL) 회원을 찾아 비밀번호 검증 후 JWT 발급
    public MemberResDTO.Login login(MemberReqDTO.LoginDto request) {
        Member member = memberRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new MemberException(MemberErrorCode.MEMBER_NOT_FOUND));

        if (!passwordEncoder.matches(request.getPassword(), member.getPassword())) {
            throw new MemberException(MemberErrorCode.INVALID_PASSWORD);
        }

        // AuthMember.getUsername() = member.getSocialUid() = email (일반 회원)
        // → JWT Subject에 email이 들어가고, social_type=LOCAL claim 포함
        String accessToken = jwtUtil.createAccessToken(new AuthMember(member));
        return MemberConverter.toLogin(accessToken);
    }

    // 회원가입
    @Transactional
    public Member join(MemberReqDTO.JoinDto request) {
        // 중복 이메일 체크
        if (memberRepository.existsByEmail(request.getEmail())) {
            throw new MemberException(MemberErrorCode.DUPLICATE_EMAIL);
        }

        String encodedPassword = passwordEncoder.encode(request.getPassword());
        // socialUid = email, socialType = LOCAL 로 변환됨
        Member newMember = MemberConverter.toMember(request, encodedPassword);

        List<Food> foodList = request.getPreferFoods().stream()
                .map(foodId -> foodRepository.findById(foodId)
                        .orElseThrow(() -> new RuntimeException("해당 음식 카테고리를 찾을 수 없습니다: " + foodId)))
                .toList();

        List<MemberFood> memberFoodList = foodList.stream()
                .map(food -> MemberFood.builder().member(newMember).food(food).build())
                .collect(Collectors.toList());
        newMember.getMemberFoodList().addAll(memberFoodList);

        List<Term> termList = request.getAgreeTerms().stream()
                .map(termId -> termRepository.findById(termId)
                        .orElseThrow(() -> new RuntimeException("해당 약관을 찾을 수 없습니다: " + termId)))
                .toList();

        List<MemberTerm> memberTermList = termList.stream()
                .map(term -> MemberTerm.builder().member(newMember).term(term).build())
                .collect(Collectors.toList());
        newMember.getMemberTermList().addAll(memberTermList);

        return memberRepository.save(newMember);
    }
}