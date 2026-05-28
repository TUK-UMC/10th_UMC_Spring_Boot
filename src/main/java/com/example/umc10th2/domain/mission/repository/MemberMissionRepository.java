package com.example.umc10th2.domain.mission.repository;

import com.example.umc10th2.domain.mission.entity.mapping.MemberMission;
import com.example.umc10th2.domain.mission.enums.MemberMissionStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface MemberMissionRepository extends JpaRepository<MemberMission, Long> {

    // ✅ 내가 진행중/완료한 미션 (오프셋 기반 페이지네이션)
    @Query("SELECT mm FROM MemberMission mm " +
           "JOIN FETCH mm.mission m " +
           "JOIN FETCH m.store s " +
           "WHERE mm.member.id = :memberId AND mm.status = :status")
    Page<MemberMission> findByMemberIdAndStatus(
            @Param("memberId") Long memberId,
            @Param("status") MemberMissionStatus status,
            Pageable pageable
    );

    boolean existsByMemberIdAndMissionId(Long memberId, Long missionId);
}
