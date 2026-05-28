package com.example.toy.domain.member.service;

import com.example.toy.domain.member.converter.MemberConverter;
import com.example.toy.domain.member.dto.MemberReqDTO;
import com.example.toy.domain.member.dto.MemberResDTO;
import com.example.toy.domain.member.entity.Food;
import com.example.toy.domain.member.entity.Member;
import com.example.toy.domain.member.entity.Term;
import com.example.toy.domain.member.entity.mapping.MemberFood;
import com.example.toy.domain.member.entity.mapping.MemberTerm;
import com.example.toy.domain.member.exception.MemberException;
import com.example.toy.domain.member.exception.code.MemberErrorCode;
import com.example.toy.domain.member.repository.FoodRepository;
import com.example.toy.domain.member.repository.MemberRepository;
import com.example.toy.domain.member.repository.TermRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;
    private final FoodRepository foodRepository;
    private final TermRepository termRepository;
    private final PasswordEncoder passwordEncoder;

    public String singleParameter(String singleParameter) {
        return singleParameter;
    }

    public MemberResDTO.GetInfo getInfo(MemberReqDTO.GetInfo dto) {
        Long memberId = dto.id();
        return getProfile(memberId);
    }

    public MemberResDTO.GetInfo getProfile(Long memberId) {
        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new MemberException(MemberErrorCode.MEMBER_NOT_FOUND));
        return MemberConverter.toGetInfo(member);
    }

    @Transactional
    public MemberResDTO.SignUpResult signUp(MemberReqDTO.SignUp dto) {
        if (memberRepository.findByEmail(dto.getEmail()).isPresent()) {
            throw new MemberException(MemberErrorCode.MEMBER_ALREADY_EXISTS);
        }

        String encodedPassword = passwordEncoder.encode(dto.getPassword());

        Member member = memberRepository.save(MemberConverter.toMember(dto, encodedPassword));

        List<Term> terms = termRepository.findAllById(dto.getTermIds());
        terms.forEach(term -> {
            MemberTerm memberTerm = MemberTerm.builder()
                    .member(member)
                    .term(term)
                    .build();
            member.getMemberTermList().add(memberTerm);
        });

        if (dto.getFoodIds() != null && !dto.getFoodIds().isEmpty()) {
            List<Food> foods = foodRepository.findAllById(dto.getFoodIds());
            foods.forEach(food -> {
                MemberFood memberFood = MemberFood.builder()
                        .member(member)
                        .food(food)
                        .build();
                member.getMemberFoodList().add(memberFood);
            });
        }

        return MemberConverter.toSignUpResult(member);
    }
}
