package com.team2002.capstone.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ReadingGoalResponseDTO {
    private Long id;
    private int year;
    private int targetBooks;
}
