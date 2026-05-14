package com.example.toy.domain.review.service;

import com.example.toy.domain.member.entity.Member;
import com.example.toy.domain.member.exception.MemberException;
import com.example.toy.domain.member.exception.code.MemberErrorCode;
import com.example.toy.domain.member.repository.MemberRepository;
import com.example.toy.domain.mission.entity.Store;
import com.example.toy.domain.mission.repository.StoreRepository;
import com.example.toy.domain.review.converter.ReviewConverter;
import com.example.toy.domain.review.dto.ReviewReqDTO;
import com.example.toy.domain.review.dto.ReviewResDTO;
import com.example.toy.domain.review.entity.Review;
import com.example.toy.domain.review.repository.ReviewRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Slice;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ReviewService {
    private final ReviewRepository reviewRepository;
    private final MemberRepository memberRepository;
    private final StoreRepository storeRepository;

    public ReviewResDTO.Pagination<ReviewResDTO.GetReviewDTO> getReviewList(Long memberId, Integer pageSize, String cursor) {
        PageRequest pageRequest = PageRequest.of(0, pageSize);
        Slice<Review> reviewSlice;

        if (cursor.equals("-1")) {
            // 첫 번째 조회
            reviewSlice = reviewRepository.findReviewsByMemberIdOrderByIdDesc(memberId, pageRequest);
        } else {
            // 커서 기반 조회
            Long idCursor = Long.parseLong(cursor.split(":")[1]);
            reviewSlice = reviewRepository.findReviewsByMemberIdAndIdLessThanOrderByIdDesc(memberId, idCursor, pageRequest);
        }

        return ReviewConverter.toPagination(reviewSlice);
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
