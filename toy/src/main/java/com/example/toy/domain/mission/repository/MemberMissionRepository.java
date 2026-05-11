package com.example.toy.domain.mission.repository;

import com.example.toy.domain.mission.entity.mapping.MemberMission;
import com.example.toy.domain.mission.enums.MissionStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface MemberMissionRepository extends JpaRepository<MemberMission, Long> {
    @Query("SELECT mm FROM MemberMission mm WHERE mm.member.id = :memberId AND mm.status = :status")
    Page<MemberMission> findAllByMemberIdAndStatus(@Param("memberId") Long memberId,
                                                   @Param("status") MissionStatus status,
                                                   Pageable pageable);
}
