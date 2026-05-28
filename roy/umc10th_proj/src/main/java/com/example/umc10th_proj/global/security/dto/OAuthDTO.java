package com.example.umc10th_proj.global.security.dto;

import com.example.umc10th_proj.domain.member.enums.SocialType;

public interface OAuthDTO {
    SocialType getSocialType();
    String getSocialUid();
    String getSocialEmail();
    String getName();
}