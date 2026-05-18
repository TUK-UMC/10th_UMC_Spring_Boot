package com.example.umc10th_proj.global.security.handler;

import com.example.umc10th_proj.global.apiPayload.code.GeneralErrorCode;
import com.example.umc10th_proj.global.security.util.HttpResponseUtil;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class CustomEntryPoint implements AuthenticationEntryPoint {

    @Override
    public void commence(
            HttpServletRequest request,
            HttpServletResponse response,
            AuthenticationException authException
    ) throws IOException {
        // 공통 Util을 사용하여 UNAUTHORIZED(401) 에러 응답 처리
        HttpResponseUtil.setErrorResponse(response, GeneralErrorCode.UNAUTHORIZED);
    }
}