package com.team2002.capstone.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class ReviewDto {
    private Long itemId;      // 저장한 책의 itemid
    private String content;   // 감상문 내용
    private Double rating;      // 별점
    private boolean isPublic; // 공개 여부
}
