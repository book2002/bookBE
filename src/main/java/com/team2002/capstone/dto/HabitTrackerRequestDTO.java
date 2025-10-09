package com.team2002.capstone.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class HabitTrackerRequestDTO {
    @NotNull(message = "날짜 입력은 필수입니다.")
    private LocalDate recordDate;
}
