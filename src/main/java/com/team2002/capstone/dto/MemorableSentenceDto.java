package com.team2002.capstone.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class MemorableSentenceDto {
    private Long itemId;      // 필수: 어떤 책에 대한 문장인지
    private String content;   // 필수: 기억하고 싶은 문장
    private Integer page;       // 페이지 번호 (선택)
}
