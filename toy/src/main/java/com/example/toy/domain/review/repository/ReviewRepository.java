package com.example.toy.domain.review.repository;

import com.example.toy.domain.review.entity.Review;
import org.springframework.data.domain.Slice;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ReviewRepository extends JpaRepository<Review, Long> {

    // 커서 없이 첫 조회
    @Query("SELECT r FROM Review r WHERE r.member.id = :memberId ORDER BY r.id DESC")
    Slice<Review> findReviewsByMemberIdOrderByIdDesc(@Param("memberId") Long memberId, Pageable pageable);

    // 커서 있을 때 조회 (idCursor보다 작은 id만)
    @Query("SELECT r FROM Review r WHERE r.member.id = :memberId AND r.id < :idCursor ORDER BY r.id DESC")
    Slice<Review> findReviewsByMemberIdAndIdLessThanOrderByIdDesc(@Param("memberId") Long memberId,
                                                                   @Param("idCursor") Long idCursor,
                                                                   Pageable pageable);
}
