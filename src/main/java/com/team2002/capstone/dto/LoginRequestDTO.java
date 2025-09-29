package com.team2002.capstone.dto;

import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
@AllArgsConstructor
public class LoginRequestDTO {
    @NotEmpty(message = "이메일 입력은 필수 입니다.")
    private String email;
    @NotEmpty(message = "비밀번호 입력은 필수 입니다.")
    private String password;
}
