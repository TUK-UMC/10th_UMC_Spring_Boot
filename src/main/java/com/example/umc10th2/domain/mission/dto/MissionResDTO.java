package com.example.umc10th2.domain.mission.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.List;

public class MissionResDTO {

    @Getter
    @Builder
    @AllArgsConstructor
    public static class MyMissionResponse {
        private Long memberMissionId;
        private Long missionId;
        private String storeName;
        private String missionSpec;
        private Integer point;
        private LocalDateTime deadline;
        private String status;
    }

    @Getter
    @Builder
    @AllArgsConstructor
    public static class MyMissionPageResponse {
        private List<MyMissionResponse> missions;
        private int currentPage;
        private int totalPages;
        private long totalElements;
        private boolean isFirst;
        private boolean isLast;
    }
}
