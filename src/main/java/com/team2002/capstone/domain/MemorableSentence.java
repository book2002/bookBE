package com.team2002.capstone.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.team2002.capstone.dto.MemorableSentenceDto;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor
public class MemorableSentence {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long sentenceId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "item_id", nullable = false)
    @JsonBackReference
    private BookShelfItem bookShelfItem;

    @Lob
    @Column(nullable = false)
    private String content;

    private Integer page;

    @Column(updatable = false)
    private LocalDateTime createdAt;

    // DTO를 바탕으로 객체를 생성하는 생성자
    public MemorableSentence(MemorableSentenceDto dto, BookShelfItem bookShelfItem) {
        this.bookShelfItem = bookShelfItem;
        this.content = dto.getContent();
        this.page = dto.getPage();
        this.createdAt = LocalDateTime.now();
    }

    // 수정 기능
    public void update(MemorableSentenceDto dto) {
        this.content = dto.getContent();
        this.page = dto.getPage();
    }
}
