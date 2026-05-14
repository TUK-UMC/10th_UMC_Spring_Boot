package com.example.umc10th.domain.review.repository;

import com.example.umc10th.domain.review.entity.Review;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ReviewRepository extends JpaRepository<Review, Long> {

    @Query(
            value = "SELECT r FROM Review r " +
                    "JOIN FETCH r.member m " +
                    "WHERE r.store.id = :storeId " +
                    "ORDER BY r.createdAt DESC",
            countQuery = "SELECT COUNT(r) FROM Review r " +
                    "WHERE r.store.id = :storeId"
    )
    Page<Review> findAllByStoreId(
            @Param("storeId") Long storeId,
            Pageable pageable
    );

    /**
     * 내가 작성한 리뷰 - ID(최신) 순 커서 기반 조회
     * - 사진(ReviewPhoto)은 fetch 하지 않음
     * - cursorId 가 null 이면 처음부터 조회
     * - cursorId 가 있으면 그 ID 보다 작은 리뷰(=더 이전 리뷰)부터 size 만큼 조회
     */
    @Query("SELECT r FROM Review r " +
            "JOIN FETCH r.store s " +
            "WHERE r.member.id = :memberId " +
            "AND (:cursorId IS NULL OR r.id < :cursorId) " +
            "ORDER BY r.id DESC")
    Slice<Review> findMyReviewsByIdCursor(
            @Param("memberId") Long memberId,
            @Param("cursorId") Long cursorId,
            Pageable pageable
    );

    /**
     * 내가 작성한 리뷰 - 별점 순(내림차순) 커서 기반 조회
     * - 사진(ReviewPhoto)은 fetch 하지 않음
     * - 별점이 같으면 id 내림차순으로 tie-break
     * - cursorScore, cursorId 가 모두 null 이면 처음부터 조회
     * - 둘 다 있으면 (score < cursorScore) 또는 (score = cursorScore AND id < cursorId) 인 리뷰부터 조회
     */
    @Query("SELECT r FROM Review r " +
            "JOIN FETCH r.store s " +
            "WHERE r.member.id = :memberId " +
            "AND (" +
            "   :cursorScore IS NULL " +
            "   OR r.score < :cursorScore " +
            "   OR (r.score = :cursorScore AND r.id < :cursorId)" +
            ") " +
            "ORDER BY r.score DESC, r.id DESC")
    Slice<Review> findMyReviewsByScoreCursor(
            @Param("memberId") Long memberId,
            @Param("cursorScore") Float cursorScore,
            @Param("cursorId") Long cursorId,
            Pageable pageable
    );
}
