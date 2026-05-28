package com.example.umc10th2.domain.mission.entity;

import com.example.umc10th2.domain.review.entity.Review;
import com.example.umc10th2.global.common.BaseEntity;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Table(name = "store")
public class Store extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 50)
    private String name;

    @Column(nullable = false, length = 20)
    private String managerNumber;

    @Column(nullable = false)
    private String detailAddress;

    @Builder.Default
    private Float score = 0.0f;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "location_id")
    private Location location;

    @OneToMany(mappedBy = "store", cascade = CascadeType.REMOVE)
    @Builder.Default
    private List<Mission> missionList = new ArrayList<>();

    @OneToMany(mappedBy = "store", cascade = CascadeType.REMOVE)
    @Builder.Default
    private List<Review> reviewList = new ArrayList<>();
}
