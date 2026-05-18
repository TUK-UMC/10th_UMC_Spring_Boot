package com.example.umc10th_proj.domain.member.service;

import com.example.umc10th_proj.domain.member.dto.MemberReqDTO;
import com.example.umc10th_proj.domain.member.dto.MemberResDTO;
import com.example.umc10th_proj.domain.member.entity.Food;
import com.example.umc10th_proj.domain.member.entity.Member;
import com.example.umc10th_proj.domain.member.entity.Term;
import com.example.umc10th_proj.domain.member.entity.mapping.MemberFood;
import com.example.umc10th_proj.domain.member.entity.mapping.MemberTerm;
import com.example.umc10th_proj.domain.member.exception.MemberException;
import com.example.umc10th_proj.domain.member.exception.code.MemberErrorCode;
import com.example.umc10th_proj.domain.member.repository.*;
import com.example.umc10th_proj.domain.member.converter.MemberConverter;
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

    public MemberResDTO.GetInfo getInfo(MemberReqDTO.GetInfo dto) {
        Member member = memberRepository.findByNameAndDeletedAtIsNull("마크")
                .orElseThrow(() -> new MemberException(MemberErrorCode.MEMBER_NOT_FOUND));
        return MemberConverter.toGetInfo(member);
    }

    @Transactional
    public Member join(MemberReqDTO.JoinDto request) {
        // 1. 비밀번호 암호화 (BCrypt)
        String encodedPassword = passwordEncoder.encode(request.getPassword());

        // 2. Member 엔티티 생성
        Member newMember = MemberConverter.toMember(request, encodedPassword);

        // 3. 선호 음식 매핑 (List<Long> -> List<MemberFood>)
        List<Food> foodList = request.getPreferFoods().stream()
                .map(foodId -> foodRepository.findById(foodId)
                        .orElseThrow(() -> new RuntimeException("해당 음식 카테고리를 찾을 수 없습니다: " + foodId)))
                .toList();

        List<MemberFood> memberFoodList = foodList.stream()
                .map(food -> MemberFood.builder().member(newMember).food(food).build())
                .collect(Collectors.toList());
        newMember.getMemberFoodList().addAll(memberFoodList); // Cascade 처리됨

        // 4. 약관 동의 매핑 (List<Long> -> List<MemberTerm>)
        List<Term> termList = request.getAgreeTerms().stream()
                .map(termId -> termRepository.findById(termId)
                        .orElseThrow(() -> new RuntimeException("해당 약관을 찾을 수 없습니다: " + termId)))
                .toList();

        List<MemberTerm> memberTermList = termList.stream()
                .map(term -> MemberTerm.builder().member(newMember).term(term).build())
                .collect(Collectors.toList());
        newMember.getMemberTermList().addAll(memberTermList); // Cascade 처리됨

        // 5. DB 저장
        return memberRepository.save(newMember);
    }
}