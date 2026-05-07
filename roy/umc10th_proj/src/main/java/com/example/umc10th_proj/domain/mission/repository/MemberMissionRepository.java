package com.example.umc10th_proj.domain.mission.repository;

import com.example.umc10th_proj.domain.mission.entity.mapping.MemberMission;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface MemberMissionRepository extends JpaRepository<MemberMission, Long> {

    @Query("SELECT mm FROM MemberMission mm " +
            "WHERE mm.member.id = :memberId " +
            "ORDER BY mm.createdAt DESC")
    Page<MemberMission> findByMemberId(
            @Param("memberId") Long memberId,
            Pageable pageable
    );
}