package com.example.umc10th_proj.global.apiPayload.handler;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.example.umc10th_proj.global.apiPayload.ApiResponse;
import com.example.umc10th_proj.global.apiPayload.code.BaseErrorCode;
import com.example.umc10th_proj.global.apiPayload.code.GeneralErrorCode;
import com.example.umc10th_proj.global.apiPayload.exception.ProjectException;

@RestControllerAdvice
public class GeneralExceptionAdvice {

    @ExceptionHandler(ProjectException.class)
    public ResponseEntity<ApiResponse<Void>> handleProjectException(ProjectException e) {
        BaseErrorCode errorCode = e.getErrorCode();
        return ResponseEntity.status(errorCode.getStatus())
                .body(ApiResponse.onFailure(errorCode, null));
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiResponse<String>> handleException(Exception ex) {
        BaseErrorCode code = GeneralErrorCode.INTERNAL_SERVER_ERROR;

        // 보안 취약점 제거: ex.getMessage()를 절대 클라이언트에게 내려주지 않음
        // 실제 운영에서는 여기서 log.error("Unhandled Exception: ", ex); 를 찍어야 함
        return ResponseEntity.status(code.getStatus())
                .body(ApiResponse.onFailure(code, "서버 내부 오류가 발생했습니다."));
    }
}