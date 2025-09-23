package com.team2002.capstone.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MemberUpdateRequestDTO {
    private String oldPassword;
    private String newPassword;
}
