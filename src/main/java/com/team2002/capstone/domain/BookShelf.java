package com.team2002.capstone.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
public class BookShelf {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long shelfId;

    // profile_id는 나중에 사용자 기능 추가 시 연결
    // private Long profileId;

    @Column(nullable = false)
    private String shelfName;

    public BookShelf(String shelfName) {
        this.shelfName = shelfName;
    }
}
