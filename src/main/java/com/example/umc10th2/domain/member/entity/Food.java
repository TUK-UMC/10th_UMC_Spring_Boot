package com.example.umc10th2.domain.member.entity;

import com.example.umc10th2.domain.member.entity.mapping.MemberFood;
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
@Table(name = "food")
public class Food extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 20)
    private String name;

    @OneToMany(mappedBy = "food", cascade = CascadeType.REMOVE)
    @Builder.Default
    private List<MemberFood> memberFoodList = new ArrayList<>();
}
