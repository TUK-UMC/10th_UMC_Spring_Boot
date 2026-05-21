package com.example.umc10th2.domain.member.repository;

import com.example.umc10th2.domain.member.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, Long> {
    Optional<Member> findByEmailAndDeletedAtIsNull(String email);
    boolean existsByEmail(String email);
}
