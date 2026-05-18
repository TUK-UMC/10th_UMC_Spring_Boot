package com.example.umc10th_proj.domain.member.repository;

import com.example.umc10th_proj.domain.member.entity.Term;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TermRepository extends JpaRepository<Term, Long> {
}