package com.team2002.capstone.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import java.util.List;

@Getter
@NoArgsConstructor
public class BookDto {

    private String title;       // 도서 제목
    private List<String> authors; // 도서 저자 리스트
    private String publisher;   // 출판사
    private String isbn;        // ISBN
    private String thumbnail;   // 책 표지 이미지 URL (카카오 API에서는 'thumbnail' 이라는 이름 사용)
    private String datetime;    // 출판일시
    private String contents;    // 책 소개
}
