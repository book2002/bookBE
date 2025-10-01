package com.team2002.capstone.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import java.util.List;

@Getter
@NoArgsConstructor
public class KakaoBookSearchResponseDto {

    // API 응답에서 책 목록을 담고 있는 필드
    private List<BookDto> documents;
}