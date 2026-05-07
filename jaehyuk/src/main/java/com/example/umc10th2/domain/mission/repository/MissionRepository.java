package com.example.umc10th2.domain.mission.repository;

import com.example.umc10th2.domain.mission.entity.Mission;
import com.example.umc10th2.domain.mission.enums.Address;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface MissionRepository extends JpaRepository<Mission, Long> {

    // 홈화면: 해당 지역에서 도전 가능한(아직 내가 도전 안 한) 미션 목록
    @Query("""
        SELECT m FROM Mission m
        JOIN m.store s
        JOIN s.location l
        WHERE l.address = :address
          AND m.deletedAt IS NULL
          AND NOT EXISTS (
              SELECT mm FROM MemberMission mm
              WHERE mm.mission = m
                AND mm.member.id = :memberId
          )
    """)
    Page<Mission> findAvailableMissionsByAddress(
            @Param("address") Address address,
            @Param("memberId") Long memberId,
            Pageable pageable
    );
}
