package com.example.umc10th2.domain.review.service;

import com.example.umc10th2.domain.member.entity.Member;
import com.example.umc10th2.domain.member.repository.MemberRepository;
import com.example.umc10th2.domain.mission.entity.Store;
import com.example.umc10th2.domain.mission.repository.StoreRepository;
import com.example.umc10th2.domain.review.converter.ReviewConverter;
import com.example.umc10th2.domain.review.dto.ReviewReqDTO;
import com.example.umc10th2.domain.review.dto.ReviewResDTO;
import com.example.umc10th2.domain.review.entity.Review;
import com.example.umc10th2.domain.review.repository.ReviewRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ReviewService {

    private final ReviewRepository reviewRepository;
    private final MemberRepository memberRepository;
    private final StoreRepository storeRepository;

    @Transactional
    public ReviewResDTO.CreateRes createReview(Long memberId, ReviewReqDTO req) {
        Member member = memberRepository.findByIdAndDeletedAtIsNull(memberId)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 회원입니다."));

        Store store = storeRepository.findById(req.getStoreId())
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 가게입니다."));

        Review review = ReviewConverter.toReview(req, member, store);
        reviewRepository.save(review);

        return ReviewConverter.toCreateRes(review);
    }
}
