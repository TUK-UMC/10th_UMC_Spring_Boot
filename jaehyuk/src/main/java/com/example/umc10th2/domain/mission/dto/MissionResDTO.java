package com.example.umc10th2.domain.mission.dto;

import com.example.umc10th2.domain.mission.enums.MissionStatus;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;
import java.util.List;

public class MissionResDTO {

    @Getter
    @Builder
    public static class MyMissionItem {
        private Long memberMissionId;
        private Long missionId;
        private String storeName;
        private String introduction;
        private Integer point;
        private LocalDate deadline;
        private MissionStatus status;
    }

    @Getter
    @Builder
    public static class MyMissionPageRes {
        private List<MyMissionItem> content;
        private int page;
        private int size;
        private long totalElements;
        private int totalPages;
        private boolean last;
    }

    @Getter
    @Builder
    public static class HomeMissionItem {
        private Long missionId;
        private String storeName;
        private String introduction;
        private Integer point;
        private LocalDate deadline;
        private String address;
    }

    @Getter
    @Builder
    public static class HomeMissionPageRes {
        private List<HomeMissionItem> content;
        private int page;
        private int size;
        private long totalElements;
        private int totalPages;
        private boolean last;
    }
}
