package com.example.umc10th_proj.domain.member.entity;

import com.example.umc10th_proj.domain.member.entity.mapping.MemberFood;
import com.example.umc10th_proj.domain.member.entity.mapping.MemberTerm;
import com.example.umc10th_proj.domain.member.enums.Gender;
import com.example.umc10th_proj.domain.member.enums.SocialType;
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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "member_id")
    private Long id;

    // OAuth 회원은 5자 초과 이름일 수 있으므로 length 확장
    @Column(name = "name", nullable = false, length = 50)
    private String name;

    @Column(name = "gender", nullable = false)
    @Enumerated(EnumType.STRING)
    @Builder.Default
    private Gender gender = Gender.NONE;

    // OAuth 회원은 생년월일 미제공 가능 → nullable = true
    @Column(name = "birth", nullable = true)
    private LocalDate birth;

    @Column(name = "address", nullable = false)
    @Enumerated(EnumType.STRING)
    @Builder.Default
    private Address address = Address.NONE;

    // OAuth 회원은 상세주소 미제공 가능 → nullable = true
    @Column(name = "detail_address", nullable = true, length = 255)
    private String detailAddress;

    // 일반 로그인: email을 uid로 / OAuth: provider uid (NOT NULL)
    @Column(name = "social_uid", nullable = false, length = 255)
    private String socialUid;

    // 일반 로그인: LOCAL / OAuth: KAKAO 등 (NOT NULL, 기본값 LOCAL)
    @Column(name = "social_type", nullable = false)
    @Enumerated(EnumType.STRING)
    @Builder.Default
    private SocialType socialType = SocialType.LOCAL;

    @Column(name = "point")
    @Builder.Default
    private Integer point = 0;

    // OAuth 회원은 email이 없을 수 있으므로 nullable = true
    @Column(name = "email", nullable = true, length = 50)
    private String email;

    // OAuth 회원은 비밀번호 없음 → nullable = true
    @Column(name = "password", nullable = true)
    private String password;

    @Column(name = "phone_number", length = 11)
    private String phoneNumber;

    @Column(name = "profile_url", length = 255)
    private String profileUrl;

    @OneToMany(mappedBy = "member", cascade = CascadeType.ALL)
    @Builder.Default
    private List<MemberFood> memberFoodList = new ArrayList<>();

    @OneToMany(mappedBy = "member", cascade = CascadeType.ALL)
    @Builder.Default
    private List<MemberTerm> memberTermList = new ArrayList<>();
}