package com.example.umc10th_proj.domain.member.entity;

import com.example.umc10th_proj.domain.member.entity.mapping.MemberFood;
import com.example.umc10th_proj.domain.member.entity.mapping.MemberTerm;
import com.example.umc10th_proj.domain.member.enums.Gender;
import com.example.umc10th_proj.domain.member.enums.SocailType;
import com.example.umc10th_proj.domain.mission.enums.Address;
import com.example.umc10th_proj.global.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "member")
public class Member extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // SEQUENCE → IDENTITY 변경
    @Column(name = "member_id")
    private Long id;

    @Column(name = "name", nullable = false, length = 5)
    private String name;

    @Column(name = "gender", nullable = false)
    @Enumerated(EnumType.STRING)
    @Builder.Default
    private Gender gender = Gender.NONE;

    @Column(name = "birth", nullable = false)
    private LocalDate birth;

    @Column(name = "address", nullable = false)
    @Enumerated(EnumType.STRING)
    @Builder.Default
    private Address address = Address.NONE;

    @Column(name = "detail_address", nullable = false, length = 255)
    private String detailAddress;

    // DB 컬럼명이 social_uid (JPA 생성) — 유지
    @Column(name = "social_uid", nullable = false, length = 255)
    private String socialUid;

    @Column(name = "social_type", nullable = false)
    @Enumerated(EnumType.STRING)
    private SocailType socialType;

    @Column(name = "point")
    @Builder.Default
    private Integer point = 0;

    @Column(name = "email", length = 50)
    private String email;

    @Column(name = "phone_number", length = 11)
    private String phoneNumber;

    // DB가 varchar(255) — TEXT → varchar(255) 변경
    @Column(name = "profile_url", length = 255)
    private String profileUrl;

    @OneToMany(mappedBy = "member", cascade = CascadeType.ALL)
    @Builder.Default
    private List<MemberFood> memberFoodList = new ArrayList<>();

    @OneToMany(mappedBy = "member", cascade = CascadeType.ALL)
    @Builder.Default
    private List<MemberTerm> memberTermList = new ArrayList<>();
}