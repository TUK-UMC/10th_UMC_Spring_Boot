package com.example.umc10th2.domain.mission.dto;

import com.example.umc10th2.domain.mission.enums.MemberMissionStatus;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;

public class MissionReqDTO {

    // ✅ 내가 진행중인 미션 조회 요청
    @Getter
    @NoArgsConstructor
    public static class MyMissionRequest {

        @NotNull(message = "회원 ID는 필수입니다.")
        private Long memberId;

        @NotNull(message = "미션 상태는 필수입니다.")
        private MemberMissionStatus status;

        @Min(value = 0, message = "페이지는 0 이상이어야 합니다.")
        private int page = 0;
    }
}
