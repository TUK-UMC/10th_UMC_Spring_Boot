package com.example.umc10th_proj.domain.mission.repository;

import com.example.umc10th_proj.domain.mission.entity.Mission;
import com.example.umc10th_proj.domain.mission.enums.Address;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface MissionRepository extends JpaRepository<Mission, Long> {

    Page<Mission> findAllByStoreId(Long storeId, Pageable pageable);

    @Query("SELECT m FROM Mission m " +
            "WHERE m.store.location.name = :address " +
            "AND (m.deadline IS NULL OR m.deadline >= CURRENT_DATE) " +
            "ORDER BY m.createdAt DESC")
    Page<Mission> findAvailableMissionsByAddress(
            @Param("address") Address address,
            Pageable pageable
    );
}