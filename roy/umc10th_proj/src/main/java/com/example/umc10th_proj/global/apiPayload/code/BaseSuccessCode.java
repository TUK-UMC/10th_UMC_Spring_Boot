package com.example.umc10th_proj.global.apiPayload.code;

public interface BaseSuccessCode {

    HttpStatus getStatus();
    String getCode();
    String getMessage();
}