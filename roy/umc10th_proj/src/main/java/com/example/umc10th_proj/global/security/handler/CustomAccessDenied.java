package com.example.umc10th_proj.global.security.handler;

import com.example.umc10th_proj.global.apiPayload.code.GeneralErrorCode;
import com.example.umc10th_proj.global.security.util.HttpResponseUtil;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class CustomAccessDenied implements AccessDeniedHandler {

    @Override
    public void handle(
            HttpServletRequest request,
            HttpServletResponse response,
            AccessDeniedException accessDeniedException
    ) throws IOException {
        // 공통 Util을 사용하여 FORBIDDEN(403) 에러 응답 처리
        HttpResponseUtil.setErrorResponse(response, GeneralErrorCode.FORBIDDEN);
    }
}