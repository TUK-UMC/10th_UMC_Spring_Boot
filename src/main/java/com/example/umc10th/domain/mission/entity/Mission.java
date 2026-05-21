package com.example.umc10th.domain.mission.entity;

import com.example.umc10th.domain.mission.entity.mapping.MemberMission;
import com.example.umc10th.global.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Table(name = "mission")
public class Mission extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // 보상 포인트 (예: 500P)
    @Column(nullable = false)
    private Integer reward;

    // 미션 내용 (예: "12,000원 이상의 식사를 하세요!")
    @Column(nullable = false, length = 200)
    private String content;

    // 미션 마감일 (예: D-7)
    @Column(name = "deadline")
    private LocalDate deadline;

    // 어느 가게의 미션인지 연관관계 매핑
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "store_id", nullable = false)
    private Store store;

    // 회원-미션 매핑 (역방향)
    @OneToMany(mappedBy = "mission", cascade = CascadeType.ALL)
    @Builder.Default
    private List<MemberMission> memberMissionList = new ArrayList<>();
}