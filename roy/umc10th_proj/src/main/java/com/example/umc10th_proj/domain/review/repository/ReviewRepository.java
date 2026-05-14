package com.example.umc10th_proj.domain.review.repository;

import com.example.umc10th_proj.domain.review.entity.Review;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.math.BigDecimal;

public interface ReviewRepository extends JpaRepository<Review, Long> {

    // 1. ID 순 (최신순) 커서 페이징
    @Query("SELECT r FROM Review r JOIN FETCH r.store " +
            "WHERE r.member.id = :memberId AND (:cursorId IS NULL OR r.id < :cursorId) " +
            "ORDER BY r.id DESC")
    Slice<Review> findMyReviewsByIdCursor(
            @Param("memberId") Long memberId,
            @Param("cursorId") Long cursorId,
            Pageable pageable
    );

    // 2. 별점 순 커서 페이징 (별점이 같으면 최신순 정렬)
    @Query("SELECT r FROM Review r JOIN FETCH r.store " +
            "WHERE r.member.id = :memberId " +
            "AND (:cursorStar IS NULL OR r.star < :cursorStar OR (r.star = :cursorStar AND r.id < :cursorId)) " +
            "ORDER BY r.star DESC, r.id DESC")
    Slice<Review> findMyReviewsByStarCursor(
            @Param("memberId") Long memberId,
            @Param("cursorStar") BigDecimal cursorStar,
            @Param("cursorId") Long cursorId,
            Pageable pageable
    );
}