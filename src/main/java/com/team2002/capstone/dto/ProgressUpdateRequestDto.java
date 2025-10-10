package com.team2002.capstone.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class ProgressUpdateRequestDto {
    private Integer currentPage;
    private Integer totalPage;
}
