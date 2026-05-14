package com.example.toy.domain.member.dto;

public class MemberReqDTO {

    public record GetInfo(Long id){};

    public record RequestBody(Long id) {}
}
