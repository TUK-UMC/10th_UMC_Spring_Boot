package com.example.umc10th_proj.global.apiPayload.exception;

import lombok.Getter;
import com.example.umc10th_proj.global.apiPayload.code.BaseErrorCode;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class ProjectException extends RuntimeException{
    private final BaseErrorCode errorCode;
}