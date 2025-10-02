package com.team2002.capstone.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.team2002.capstone.dto.ReviewDto;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor
public class Review {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long reviewId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "item_id", nullable = false) // DB에는 item_id 라는 이름의 FK 컬럼 생성
    @JsonBackReference
    private BookShelfItem bookShelfItem;

    @Lob
    @Column(nullable = false)
    private String content;

    private Double rating;
    private boolean isPublic;
    private LocalDateTime createdAt;

    public Review(ReviewDto reviewDto, BookShelfItem bookShelfItem) {
        this.bookShelfItem = bookShelfItem;
        this.content = reviewDto.getContent();
        this.rating = reviewDto.getRating();
        this.isPublic = reviewDto.isPublic();
        this.createdAt = LocalDateTime.now();
    }
}

