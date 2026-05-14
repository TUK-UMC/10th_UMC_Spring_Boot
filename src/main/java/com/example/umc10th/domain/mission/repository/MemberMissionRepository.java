package com.example.umc10th.domain.mission.repository;

import com.example.umc10th.domain.mission.entity.mapping.MemberMission;
import com.example.umc10th.domain.mission.enums.MissionStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface MemberMissionRepository extends JpaRepository<MemberMission, Long> {

    @Query("SELECT COUNT(mm) FROM MemberMission mm " +
            "WHERE mm.member.id = :memberId AND mm.status = com.example.umc10th.domain.mission.enums.MissionStatus.ONGOING")
    int countOngoingByMemberId(@Param("memberId") Long memberId);

    @Query(
            value = "SELECT mm FROM MemberMission mm " +
                    "JOIN FETCH mm.mission m " +
                    "JOIN FETCH m.store s " +
                    "WHERE mm.member.id = :memberId " +
                    "AND mm.status = :status " +
                    "ORDER BY mm.createdAt DESC",
            countQuery = "SELECT COUNT(mm) FROM MemberMission mm " +
                    "WHERE mm.member.id = :memberId " +
                    "AND mm.status = :status"
    )
    Page<MemberMission> findMyMissionsByStatus(
            @Param("memberId") Long memberId,
            @Param("status") MissionStatus status,
            Pageable pageable
    );
}