package com.example.umc10th2.domain.review.exception;

import com.example.umc10th2.domain.review.exception.code.ReviewErrorCode;
import com.example.umc10th2.global.exception.GeneralException;

public class ReviewException extends GeneralException {
    public ReviewException(ReviewErrorCode errorCode) {
        super(errorCode);
    }
}
