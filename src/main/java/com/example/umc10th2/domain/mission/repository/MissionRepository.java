package com.example.umc10th2.domain.mission.repository;

import com.example.umc10th2.domain.mission.entity.Mission;
import com.example.umc10th2.domain.mission.enums.MissionStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface MissionRepository extends JpaRepository<Mission, Long> {

    // 홈 화면: 특정 지역 도전 가능한 미션 (오프셋 페이징)
    @Query("SELECT m FROM Mission m " +
           "JOIN FETCH m.store s " +
           "JOIN FETCH s.location l " +
           "WHERE l.id = :locationId AND m.status = :status")
    Page<Mission> findAvailableMissionsByLocation(
            @Param("locationId") Long locationId,
            @Param("status") MissionStatus status,
            Pageable pageable
    );
}
