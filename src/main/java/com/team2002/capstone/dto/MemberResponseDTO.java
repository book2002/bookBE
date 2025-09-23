package com.team2002.capstone.dto;

import com.team2002.capstone.domain.enums.AccountStatusEnum;
import com.team2002.capstone.domain.enums.RoleEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class MemberResponseDTO {

    private Long memberId;
    private AccountStatusEnum status;
    private RoleEnum role;
    private LocalDateTime createdAt;
}
