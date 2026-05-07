package com.example.umc10th2.domain.review.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ReviewReqDTO {
    private Long storeId;
    private Float rating;
    private String content;
}
