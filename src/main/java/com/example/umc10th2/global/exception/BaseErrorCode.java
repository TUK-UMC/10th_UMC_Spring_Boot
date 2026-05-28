package com.example.umc10th2.global.exception;

import org.springframework.http.HttpStatus;

public interface BaseErrorCode {
    String getCode();
    String getMessage();
    HttpStatus getStatus();
}
