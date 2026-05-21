package com.example.umc10th.global.security.handler;

import com.example.umc10th.global.apiPayload.code.BaseErrorCode;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.MediaType;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

public final class SecurityResponseWriter {

    private SecurityResponseWriter() {
    }

    public static void write(HttpServletResponse response, BaseErrorCode errorCode) throws IOException {
        response.setStatus(errorCode.getStatus().value());
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        response.setCharacterEncoding(StandardCharsets.UTF_8.name());

        // ApiResponse 와 동일한 구조: { isSuccess, code, message, result }
        String body = String.format(
                "{\"isSuccess\": false, \"code\": \"%s\", \"message\": \"%s\", \"result\": null}",
                errorCode.getCode(),
                errorCode.getMessage()
        );
        response.getWriter().write(body);
    }
}
