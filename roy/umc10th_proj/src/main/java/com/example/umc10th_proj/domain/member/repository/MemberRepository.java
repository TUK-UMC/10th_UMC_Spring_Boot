package com.example.umc10th_proj.domain.member.repository;

import com.example.umc10th_proj.domain.member.entity.Member;
import com.example.umc10th_proj.domain.member.enums.SocialType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, Long> {

    Optional<Member> findByNameAndDeletedAtIsNull(String name);

    Optional<Member> findByEmail(String email);

    Optional<Member> findBySocialTypeAndSocialUid(SocialType socialType, String socialUid);

    // 회원가입 중복 이메일 체크용
    boolean existsByEmail(String email);
}