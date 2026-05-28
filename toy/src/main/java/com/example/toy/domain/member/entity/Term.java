package com.example.toy.domain.member.entity;

import com.example.toy.global.common.BaseEntity;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Table(name = "term")
public class Term extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "title")
    private String title;

    @Column(name = "body")
    private String body;

    @Column(name = "optional")
    private Boolean optional;

    @OneToMany(mappedBy = "term", cascade = CascadeType.ALL)
    private java.util.List<com.example.toy.domain.member.entity.mapping.MemberTerm> memberTermList = new java.util.ArrayList<>();
}
