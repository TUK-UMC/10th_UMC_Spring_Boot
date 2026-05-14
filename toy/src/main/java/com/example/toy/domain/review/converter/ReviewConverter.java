package com.example.toy.domain.review.converter;

import com.example.toy.domain.member.entity.Member;
import com.example.toy.domain.mission.entity.Store;
import com.example.toy.domain.review.dto.ReviewReqDTO;
import com.example.toy.domain.review.dto.ReviewResDTO;
import com.example.toy.domain.review.entity.Review;
import org.springframework.data.domain.Slice;

import java.util.List;

public class ReviewConverter {

    public static Review toReview(ReviewReqDTO.CreateReviewDTO request, Member member, Store store) {
        return Review.builder()
                .score(request.getScore())
                .body(request.getBody())
                .member(member)
                .store(store)
                .build();
    }

    public static ReviewResDTO.CreateReviewResultDTO toCreateReviewResultDTO(Review review) {
        return ReviewResDTO.CreateReviewResultDTO.builder()
                .reviewId(review.getId())
                .createdAt(review.getCreatedAt())
                .build();
    }

    public static ReviewResDTO.GetReviewDTO toGetReviewDTO(Review review) {
        return ReviewResDTO.GetReviewDTO.builder()
                .reviewId(review.getId())
                .body(review.getBody())
                .score(review.getScore())
                .storeName(review.getStore().getName())
                .createdAt(review.getCreatedAt())
                .build();
    }

    public static ReviewResDTO.Pagination<ReviewResDTO.GetReviewDTO> toPagination(Slice<Review> reviewSlice) {
        List<ReviewResDTO.GetReviewDTO> data = reviewSlice.map(ReviewConverter::toGetReviewDTO).toList();

        String nextCursor = null;
        if (reviewSlice.hasNext()) {
            Review last = reviewSlice.getContent().get(reviewSlice.getContent().size() - 1);
            nextCursor = last.getId() + ":" + last.getId();
        }

        return ReviewResDTO.Pagination.<ReviewResDTO.GetReviewDTO>builder()
                .data(data)
                .hasNext(reviewSlice.hasNext())
                .nextCursor(nextCursor)
                .pageSize(reviewSlice.getSize())
                .build();
    }
}
