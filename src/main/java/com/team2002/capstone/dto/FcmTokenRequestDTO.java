package com.team2002.capstone.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;

@Getter
public class FcmTokenRequestDTO {
    @NotBlank(message = "FCM 토큰은 필수입니다.")
    private String fcmToken;
}
