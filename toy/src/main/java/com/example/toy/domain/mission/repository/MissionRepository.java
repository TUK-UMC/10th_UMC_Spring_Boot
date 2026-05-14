package com.example.toy.domain.mission.repository;

import com.example.toy.domain.mission.entity.Mission;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface MissionRepository extends JpaRepository<Mission, Long> {
    @Query("SELECT m FROM Mission m WHERE m.store.region.id = :regionId")
    Page<Mission> findAllByRegionId(@Param("regionId") Long regionId, Pageable pageable);

    @Query("SELECT m FROM Mission m " +
           "WHERE m.store.region.name = :regionName " +
           "AND m.id NOT IN (SELECT mm.mission.id FROM MemberMission mm WHERE mm.member.id = :memberId)")
    Page<Mission> findAvailableMissionsByRegionName(@Param("regionName") String regionName, 
                                                    @Param("memberId") Long memberId, 
                                                    Pageable pageable);
}
