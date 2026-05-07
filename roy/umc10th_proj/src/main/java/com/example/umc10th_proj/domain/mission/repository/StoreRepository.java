package com.example.umc10th_proj.domain.mission.repository;

import com.example.umc10th_proj.domain.mission.entity.Store;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StoreRepository extends JpaRepository<Store, Long> {
}