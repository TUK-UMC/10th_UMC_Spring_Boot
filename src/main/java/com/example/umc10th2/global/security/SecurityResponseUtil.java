package com.example.umc10th2.global.security;

import com.example.umc10th2.global.exception.BaseErrorCode;
import com.example.umc10th2.global.response.ApiResponse;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

public class SecurityResponseUtil {

    private static final ObjectMapper objectMapper = new ObjectMapper();

    public static void writeErrorResponse(
            HttpServletResponse response,
            BaseErrorCode errorCode
    ) throws IOException {
        response.setContentType("application/json;charset=UTF-8");
        response.setStatus(errorCode.getStatus().value());
        ApiResponse<Void> errorResponse = ApiResponse.onFailure(errorCode, null);
        objectMapper.writeValue(response.getOutputStream(), errorResponse);
    }
}
