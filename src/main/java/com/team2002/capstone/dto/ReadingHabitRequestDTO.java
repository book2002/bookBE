package com.team2002.capstone.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class ReadingHabitRequestDTO {
    @Pattern(regexp = "^([0-1]?[0-9]|2[0-3]):[0-5][0-9]$", message = "시간 형식은 HH:mm 형식이어야 합니다.")
    @NotEmpty(message = "목표 시간은 필수입니다.")
    private String targetTime;
    @NotEmpty(message = "요일 설정은 필수입니다.")
    private List<String> daysOfWeek;
    private boolean isActive;
}
