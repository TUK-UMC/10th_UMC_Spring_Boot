package com.example.toy.domain.review.service;

import com.example.toy.domain.member.entity.Member;
import com.example.toy.domain.member.exception.MemberException;
import com.example.toy.domain.member.exception.code.MemberErrorCode;
import com.example.toy.domain.member.repository.MemberRepository;
import com.example.toy.domain.mission.entity.Store;
import com.example.toy.domain.mission.repository.StoreRepository;
import com.example.toy.domain.review.converter.ReviewConverter;
import com.example.toy.domain.review.dto.ReviewReqDTO;
import com.example.toy.domain.review.entity.Review;
import com.example.toy.domain.review.repository.ReviewRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ReviewService {
    private final ReviewRepository reviewRepository;
    private final MemberRepository memberRepository;
    private final StoreRepository storeRepository;

    public Page<Review> getReviewList(Long memberId, Integer page) {
        // Pageable은 0부터 시작하므로 page-1 처리
        return reviewRepository.findAllByMemberId(memberId, PageRequest.of(page - 1, 10));
    }

    @Transactional
    public Review createReview(Long storeId, ReviewReqDTO.CreateReviewDTO request) {
        Member member = memberRepository.findById(request.getMemberId())
                .orElseThrow(() -> new MemberException(MemberErrorCode.MEMBER_NOT_FOUND));

        Store store = storeRepository.findById(storeId)
                .orElseThrow(() -> new IllegalArgumentException("Store not found"));

        Review review = ReviewConverter.toReview(request, member, store);
        return reviewRepository.save(review);
    }
}
