package com.team2002.capstone.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class HabitStatusUpdateResponseDTO {
    private Long memberId;
    private boolean isActive;
}
