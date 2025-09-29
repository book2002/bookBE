package com.team2002.capstone.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ReadingGoalRequestDTO {

    @NotNull(message = "목표 연도는 필수입니다.")
    private int year;

    @NotNull(message = "목표 권수는 필수입니다.")
    @Min(value = 1, message = "목표 권수는 1권 이상이어야 합니다.")
    private int targetBooks;
}
