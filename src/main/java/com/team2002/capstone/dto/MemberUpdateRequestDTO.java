package com.team2002.capstone.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MemberUpdateRequestDTO {
    @NotEmpty(message = "비밀번호 입력은 필수 입니다.")
    private String oldPassword;
    @NotEmpty(message = "비밀번호 입력은 필수 입니다.")
    @Size(min = 8, message = "비밀번호는 최소 8자 이상이어야 합니다.")
    private String newPassword;
}
