package com.example.umc10th.domain.mission.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

public class MissionResDTO {

    // 화면에 뿌려질 미션 리스트와 페이징 정보를 담는 객체
    @Builder
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class MyMissionPreViewListDTO {
        private List<MyMissionPreViewDTO> missionList;
        private Integer listSize;
        private Integer totalPage;
        private Long totalElements;
        private Boolean isFirst;
        private Boolean isLast;
    }

    // 개별 미션 정보 (Figma 화면의 카드 1개에 해당)
    @Builder
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class MyMissionPreViewDTO {
        private Long missionId;
        private String storeName;
        private Integer rewardPoint; // 예: 500
        private String missionSpec;  // 예: "12,000원 이상의 식사를 하세요!"
        private String status;       // 예: "진행중", "성공"
    }

    public record GetMission(
            Long missionId,
            Integer point,
            String conditional
    ) {}
}