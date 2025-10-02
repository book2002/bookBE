package com.team2002.capstone.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.team2002.capstone.dto.BookDto;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor
public class BookShelfItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long itemId;

    @Column(nullable = false, unique = true)
    private String isbn;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String author;

    private String thumbnail;

    // --- BookShelf와의 관계 설정 ---
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "shelf_id", nullable = false)
    @JsonBackReference // Review와 마찬가지로 순환 참조 방지
    private BookShelf bookShelf;
    // ----------------------------

    @Enumerated(EnumType.STRING)
    private ReadState state;

    private LocalDateTime createdAt;

    // --- Review와의 관계 설정 ---
    @OneToMany(mappedBy = "bookShelfItem", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    private List<Review> reviews = new ArrayList<>();
    // --------------------------

    public enum ReadState {
        WANT_TO_READ, READING, COMPLETED
    }

    // 생성자도 BookShelf 객체를 직접 받도록 수정
    public BookShelfItem(BookDto bookDto, BookShelf bookShelf) {
        this.isbn = bookDto.getIsbn();
        this.title = bookDto.getTitle();
        this.author = String.join(", ", bookDto.getAuthors());
        this.thumbnail = bookDto.getThumbnail();
        this.bookShelf = bookShelf; // shelfId 대신 bookShelf 객체 자체를 저장
        this.state = ReadState.WANT_TO_READ;
        this.createdAt = LocalDateTime.now();
    }
}

