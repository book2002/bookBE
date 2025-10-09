package com.team2002.capstone.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Builder
public class ReadingHabitResponseDTO {
    private Long id;
    private String targetTime;
    private List<String> recurringDays;
    private boolean isActive;
}
