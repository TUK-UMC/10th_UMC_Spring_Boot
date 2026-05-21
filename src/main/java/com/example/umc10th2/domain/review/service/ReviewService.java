package com.example.umc10th2.domain.review.service;

import com.example.umc10th2.domain.member.entity.Member;
import com.example.umc10th2.domain.member.exception.MemberException;
import com.example.umc10th2.domain.member.exception.code.MemberErrorCode;
import com.example.umc10th2.domain.member.repository.MemberRepository;
import com.example.umc10th2.domain.mission.entity.Store;
import com.example.umc10th2.domain.mission.repository.StoreRepository;
import com.example.umc10th2.domain.review.converter.ReviewConverter;
import com.example.umc10th2.domain.review.dto.ReviewReqDTO;
import com.example.umc10th2.domain.review.dto.ReviewResDTO;
import com.example.umc10th2.domain.review.entity.Review;
import com.example.umc10th2.domain.review.exception.ReviewException;
import com.example.umc10th2.domain.review.exception.code.ReviewErrorCode;
import com.example.umc10th2.domain.review.repository.ReviewRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ReviewService {

    private final ReviewRepository reviewRepository;
    private final MemberRepository memberRepository;
    private final StoreRepository storeRepository;

    // ✅ 리뷰 작성
    @Transactional
    public void createReview(ReviewReqDTO.CreateReviewRequest request) {
        Member member = memberRepository.findById(request.getMemberId())
                .orElseThrow(() -> new MemberException(MemberErrorCode.MEMBER_NOT_FOUND));
        Store store = storeRepository.findById(request.getStoreId())
                .orElseThrow(() -> new ReviewException(ReviewErrorCode.STORE_NOT_FOUND));

        Review review = Review.builder()
                .body(request.getBody())
                .score(request.getScore())
                .member(member)
                .store(store)
                .build();

        reviewRepository.save(review);
    }

    // ✅ 내가 작성한 리뷰 조회 (커서 기반 - ID순)
    public ReviewResDTO.MyReviewCursorResponse getMyReviewsById(ReviewReqDTO.MyReviewRequest request) {
        memberRepository.findById(request.getMemberId())
                .orElseThrow(() -> new MemberException(MemberErrorCode.MEMBER_NOT_FOUND));

        // size+1개 조회해서 hasNext 판단
        PageRequest pageable = PageRequest.of(0, request.getSize() + 1);
        List<Review> reviews = reviewRepository.findByMemberIdOrderById(
                request.getMemberId(), request.getLastId(), pageable);

        return ReviewConverter.toCursorResponse(reviews, request.getSize(), "id");
    }

    // ✅ 내가 작성한 리뷰 조회 (커서 기반 - 별점순)
    public ReviewResDTO.MyReviewCursorResponse getMyReviewsByScore(ReviewReqDTO.MyReviewRequest request) {
        memberRepository.findById(request.getMemberId())
                .orElseThrow(() -> new MemberException(MemberErrorCode.MEMBER_NOT_FOUND));

        PageRequest pageable = PageRequest.of(0, request.getSize() + 1);
        List<Review> reviews = reviewRepository.findByMemberIdOrderByScore(
                request.getMemberId(), request.getLastScore(), request.getLastId(), pageable);

        return ReviewConverter.toCursorResponse(reviews, request.getSize(), "score");
    }
}
