package com.example.umc10th_proj.domain.review.service;

import com.example.umc10th_proj.domain.member.entity.Member;
import com.example.umc10th_proj.domain.member.exception.MemberException;
import com.example.umc10th_proj.domain.member.exception.code.MemberErrorCode;
import com.example.umc10th_proj.domain.member.repository.MemberRepository;
import com.example.umc10th_proj.domain.mission.entity.Store;
import com.example.umc10th_proj.domain.mission.repository.StoreRepository;
import com.example.umc10th_proj.domain.review.dto.ReviewReqDTO;
import com.example.umc10th_proj.domain.review.entity.Review;
import com.example.umc10th_proj.domain.review.repository.ReviewRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;

@Service
@RequiredArgsConstructor
public class ReviewService {

    private final ReviewRepository reviewRepository;
    private final MemberRepository memberRepository;
    private final StoreRepository storeRepository;

    // 리뷰 작성
    @Transactional
    public Review createReview(Long storeId, Long memberId, ReviewReqDTO.CreateReview dto) {
        Store store = storeRepository.findById(storeId)
                .orElseThrow(() -> new RuntimeException("가게를 찾을 수 없습니다."));
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
}