package com.example.umc10th2.domain.member.dto;

import java.util.List;

public class MemberReqDTO {
    private String name;
    private String email;
    private String password;
    private String phone;

    private String gender;
    private Integer birthYear;
    private String address;
    private List<String> preferredCategories;
}
