package com.example.umc10th_proj.domain.review.exception;

import com.example.umc10th_proj.global.apiPayload.code.BaseErrorCode;
import com.example.umc10th_proj.global.apiPayload.exception.ProjectException;

public class ReviewException extends ProjectException {
    public ReviewException(BaseErrorCode errorCode) {
        super(errorCode);
    }
}