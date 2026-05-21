package com.example.umc10th2.domain.mission.entity.mapping;

import com.example.umc10th2.domain.member.entity.Member;
import com.example.umc10th2.domain.mission.entity.Mission;
import com.example.umc10th2.domain.mission.enums.MemberMissionStatus;
import com.example.umc10th2.global.common.BaseEntity;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Table(name = "member_mission")
public class MemberMission extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    @Builder.Default
    private MemberMissionStatus status = MemberMissionStatus.CHALLENGING;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "mission_id")
    private Mission mission;

    // 미션 완료 처리 메서드
    public void complete() {
        this.status = MemberMissionStatus.COMPLETE;
    }
}
