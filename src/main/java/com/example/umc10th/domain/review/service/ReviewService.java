package com.example.umc10th.domain.review.service;

import com.example.umc10th.domain.member.entity.Member;
import com.example.umc10th.domain.member.exception.MemberException;
import com.example.umc10th.domain.member.exception.code.MemberErrorCode;
import com.example.umc10th.domain.member.repository.MemberRepository;
import com.example.umc10th.domain.mission.entity.Store;
import com.example.umc10th.domain.mission.exception.MissionException;
import com.example.umc10th.domain.mission.exception.code.MissionErrorCode;
import com.example.umc10th.domain.mission.repository.StoreRepository;
import com.example.umc10th.domain.review.dto.ReviewReqDTO;
import com.example.umc10th.domain.review.entity.Review;
import com.example.umc10th.domain.review.repository.ReviewRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class ReviewService {

    private static final int PAGE_SIZE = 10;

    private final ReviewRepository reviewRepository;
    private final MemberRepository memberRepository;
    private final StoreRepository storeRepository;

    /**
     * 특정 가게의 리뷰 목록 페이징 조회 (오프셋 기반)
     */
    @Transactional(readOnly = true)
    public Page<Review> getReviewList(Long storeId, Integer page) {
        // 가게 존재 여부 확인
        storeRepository.findById(storeId)
                .orElseThrow(() -> new MissionException(MissionErrorCode.STORE_NOT_FOUND));

        // PageRequest 는 0-base 이므로 한 칸 보정
        int safePage = (page == null || page < 1) ? 0 : page - 1;
        Pageable pageable = PageRequest.of(safePage, PAGE_SIZE);

        return reviewRepository.findAllByStoreId(storeId, pageable);
    }

    /**
     * 리뷰 작성
     */
    @Transactional
    public Review createReview(Long storeId, Long memberId, ReviewReqDTO.CreateReview dto) {
        Store store = storeRepository.findById(storeId)
                .orElseThrow(() -> new MissionException(MissionErrorCode.STORE_NOT_FOUND));
        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new MemberException(MemberErrorCode.MEMBER_NOT_FOUND));

        Review review = Review.builder()
                .body(dto.getBody())
                .score(dto.getScore())
                .store(store)
                .member(member)
                .build();

        return reviewRepository.save(review);
    }

    /**
     * 내가 작성한 리뷰 - ID(최신) 순 커서 기반 조회
     */
    @Transactional(readOnly = true)
    public Slice<Review> getMyReviewsById(Long memberId, Long cursorId, int size) {
        memberRepository.findById(memberId)
                .orElseThrow(() -> new MemberException(MemberErrorCode.MEMBER_NOT_FOUND));

        Pageable pageable = PageRequest.of(0, size);
        return reviewRepository.findMyReviewsByIdCursor(memberId, cursorId, pageable);
    }

    /**
     * 내가 작성한 리뷰 - 별점 순 커서 기반 조회
     * - cursorScore 가 있으면 cursorId 도 함께 필요 (tie-break)
     */
    @Transactional(readOnly = true)
    public Slice<Review> getMyReviewsByScore(Long memberId, Float cursorScore, Long cursorId, int size) {
        memberRepository.findById(memberId)
                .orElseThrow(() -> new MemberException(MemberErrorCode.MEMBER_NOT_FOUND));

        Pageable pageable = PageRequest.of(0, size);
        return reviewRepository.findMyReviewsByScoreCursor(memberId, cursorScore, cursorId, pageable);
    }
}
