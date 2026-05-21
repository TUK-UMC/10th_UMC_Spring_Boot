package com.example.umc10th2.domain.member.entity;

import com.example.umc10th2.domain.member.entity.mapping.MemberTerm;
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
@Table(name = "term")
public class Term extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 50)
    private String title;

    @Column(nullable = false)
    private Boolean optional;

    @OneToMany(mappedBy = "term", cascade = CascadeType.REMOVE)
    @Builder.Default
    private List<MemberTerm> memberTermList = new ArrayList<>();
}
