package com.team2002.capstone.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
@AllArgsConstructor
public class ProfileRequestDTO {

    @NotEmpty(message = "닉네임 입력은 필수 입니다.")
    @Size(min = 2, max = 20, message = "닉네임은 2자 이상 20자 이하여야 합니다.")
    private String nickname;
    @NotEmpty(message = "생년월일 입력은 필수 입니다.")
    @Size(min = 8, max = 8, message = "생년월일은 8자리(YYYYMMDD)로 입력해야 합니다.")
    private String birth;
    @NotEmpty(message = "성별 입력은 필수 입니다.")
    private String gender;
    @Size(max = 200, message = "소개는 200자 이하여야 합니다.")
    private String bio;
}
