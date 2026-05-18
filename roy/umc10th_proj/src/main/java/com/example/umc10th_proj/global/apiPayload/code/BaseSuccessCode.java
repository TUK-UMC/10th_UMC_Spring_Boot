package com.example.umc10th_proj.global.apiPayload.code;

import org.springframework.http.HttpStatus;

public interface BaseSuccessCode {
// 앞으로 우리 서버에서 나가는 모든 성공 응답은 반드시 3가지를 가져야 한다
    HttpStatus getStatus();
    String getCode();
    String getMessage();
}