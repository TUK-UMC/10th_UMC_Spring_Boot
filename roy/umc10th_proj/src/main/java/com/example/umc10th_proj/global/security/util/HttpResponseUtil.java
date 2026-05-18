package com.example.umc10th_proj.global.security.util;

import com.example.umc10th_proj.global.apiPayload.ApiResponse;
import com.example.umc10th_proj.global.apiPayload.code.BaseErrorCode;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

public class HttpResponseUtil {

    // ObjectMapper는 생성 비용이 비싸므로 static으로 한 번만 생성하여 재사용합니다.
    private static final ObjectMapper objectMapper = new ObjectMapper();

    // CustomAccessDenied와 CustomEntryPoint에서 공통으로 사용할 메서드
    public static void setErrorResponse(HttpServletResponse response, BaseErrorCode errorCode) throws IOException {
        // 응답 Content-Type, HTTP 상태코드 정의
        response.setContentType("application/json;charset=UTF-8");
        response.setStatus(errorCode.getStatus().value());

        // Response Body에 프로젝트 표준 규격(ApiResponse) 넣기
        ApiResponse<Void> errorResponse = ApiResponse.onFailure(errorCode, null);

        // 실제 Response로 덮어쓰기
        objectMapper.writeValue(response.getOutputStream(), errorResponse);
    }
}