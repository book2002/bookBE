package com.team2002.capstone.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@Builder
public class HabitTrackerResponseDTO {
    private Long id;
    private Long memberId;
    private LocalDate recordDate;
    private String message;
    private boolean isActive;
}
