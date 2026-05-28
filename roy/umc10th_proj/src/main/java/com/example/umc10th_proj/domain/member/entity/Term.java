package com.example.umc10th_proj.domain.member.entity;

import com.example.umc10th_proj.domain.member.enums.TermName;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Table(name = "term")
public class Term {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "term_id")
    private Long id;

    @Column(name = "name")
    @Enumerated(EnumType.STRING)
    private TermName name;
}