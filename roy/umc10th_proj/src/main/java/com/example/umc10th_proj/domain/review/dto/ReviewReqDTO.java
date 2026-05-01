package com.example.umc10th_proj.domain.review.dto;

import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;
import java.util.List;

public class ReviewReqDTO {

    @Getter
    @Setter
    public static class CreateReview {
        private Double star;
        private String content;
        private List<MultipartFile> photos; // 사진 파일 명세 그대로 포함
    }
}