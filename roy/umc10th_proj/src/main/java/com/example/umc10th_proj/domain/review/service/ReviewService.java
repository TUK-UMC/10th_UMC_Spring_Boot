package com.example.umc10th_proj.domain.review.service;

import com.example.umc10th_proj.domain.member.entity.Member;
import com.example.umc10th_proj.domain.member.exception.MemberException;
import com.example.umc10th_proj.domain.member.exception.code.MemberErrorCode;
import com.example.umc10th_proj.domain.member.repository.MemberRepository;
import com.example.umc10th_proj.domain.mission.entity.Store;
import com.example.umc10th_proj.domain.mission.repository.StoreRepository;
import com.example.umc10th_proj.domain.review.dto.ReviewReqDTO;
import com.example.umc10th_proj.domain.review.entity.Review;
import com.example.umc10th_proj.domain.review.exception.ReviewException;
import com.example.umc10th_proj.domain.review.exception.code.ReviewErrorCode;
import com.example.umc10th_proj.domain.review.repository.ReviewRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Slice;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;

@Service
@RequiredArgsConstructor
public class ReviewService {

    private final ReviewRepository reviewRepository;
    private final MemberRepository memberRepository;
    private final StoreRepository storeRepository;

    @Transactional
    public Review createReview(Long storeId, Long memberId, ReviewReqDTO.CreateReview dto) {
        Store store = storeRepository.findById(storeId)
                .orElseThrow(() -> new ReviewException(ReviewErrorCode.STORE_NOT_FOUND));

        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new MemberException(MemberErrorCode.MEMBER_NOT_FOUND));

        Review review = Review.builder()
                .content(dto.getContent())
                .star(BigDecimal.valueOf(dto.getStar()))
                .store(store)
                .member(member)
                .build();

        return reviewRepository.save(review);
    }

    public Slice<Review> getMyReviews(
            Long memberId,
            String sortType,
            Long cursorId,
            BigDecimal cursorStar,
            Integer size
    ) {
        PageRequest pageRequest = PageRequest.ofSize(size);

        if ("STAR".equalsIgnoreCase(sortType)) {
            return reviewRepository.findMyReviewsByStarCursor(memberId, cursorStar, cursorId, pageRequest);
        } else {
            return reviewRepository.findMyReviewsByIdCursor(memberId, cursorId, pageRequest);
        }
    }
}