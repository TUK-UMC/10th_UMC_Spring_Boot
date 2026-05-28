package com.example.umc10th2.domain.mission.entity;

import com.example.umc10th2.domain.mission.entity.mapping.MemberMission;
import com.example.umc10th2.domain.mission.enums.MissionStatus;
import com.example.umc10th2.global.common.BaseEntity;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Table(name = "mission")
public class Mission extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String missionSpec;

    @Column(nullable = false)
    private LocalDateTime deadline;

    @Column(nullable = false)
    private Integer point;

    @Enumerated(EnumType.STRING)
    @Builder.Default
    private MissionStatus status = MissionStatus.AVAILABLE;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "store_id")
    private Store store;

    @OneToMany(mappedBy = "mission", cascade = CascadeType.REMOVE)
    @Builder.Default
    private List<MemberMission> memberMissionList = new ArrayList<>();
}
