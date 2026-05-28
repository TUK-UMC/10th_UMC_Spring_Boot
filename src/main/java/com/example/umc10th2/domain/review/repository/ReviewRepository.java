package com.example.umc10th2.domain.review.repository;

import com.example.umc10th2.domain.review.entity.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ReviewRepository extends JpaRepository<Review, Long> {

    // ✅ 커서 기반 - ID 순 (lastId보다 큰 ID의 리뷰, limit개)
    @Query("SELECT r FROM Review r " +
           "WHERE r.member.id = :memberId AND r.id > :lastId " +
           "ORDER BY r.id ASC")
    List<Review> findByMemberIdOrderById(
            @Param("memberId") Long memberId,
            @Param("lastId") Long lastId,
            org.springframework.data.domain.Pageable pageable
    );

    // ✅ 커서 기반 - 별점 순 (lastScore보다 낮거나, 같으면 lastId보다 큰 ID)
    @Query("SELECT r FROM Review r " +
           "WHERE r.member.id = :memberId " +
           "AND (r.score < :lastScore OR (r.score = :lastScore AND r.id > :lastId)) " +
           "ORDER BY r.score DESC, r.id ASC")
    List<Review> findByMemberIdOrderByScore(
            @Param("memberId") Long memberId,
            @Param("lastScore") Float lastScore,
            @Param("lastId") Long lastId,
            org.springframework.data.domain.Pageable pageable
    );
}
