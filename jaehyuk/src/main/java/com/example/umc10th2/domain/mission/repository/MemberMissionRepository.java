package com.example.umc10th2.domain.mission.repository;

import com.example.umc10th2.domain.mission.entity.mapping.MemberMission;
import com.example.umc10th2.domain.mission.enums.MissionStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface MemberMissionRepository extends JpaRepository<MemberMission, Long> {

    // 내 미션 목록: 진행중 + 완료 필터 (status가 null이면 전체)
    @Query("""
        SELECT mm FROM MemberMission mm
        JOIN FETCH mm.mission mi
        JOIN FETCH mi.store s
        WHERE mm.member.id = :memberId
          AND (:status IS NULL OR mm.status = :status)
          AND mm.deletedAt IS NULL
    """)
    Page<MemberMission> findMyMissions(
            @Param("memberId") Long memberId,
            @Param("status") MissionStatus status,
            Pageable pageable
    );

    Optional<MemberMission> findByIdAndMemberId(Long id, Long memberId);

    boolean existsByMemberIdAndMissionId(Long memberId, Long missionId);
}
