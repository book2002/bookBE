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
    @JsonBackReference
    private BookShelf bookShelf;
    // ----------------------------

    @Enumerated(EnumType.STRING)
    private ReadState state;

    private Integer currentPage;
    private Integer totalPage;

    private LocalDateTime createdAt;

    @OneToMany(mappedBy = "bookShelfItem", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    private List<Review> reviews = new ArrayList<>();

    public enum ReadState {
        WANT_TO_READ, READING, COMPLETED
    }

    @OneToMany(mappedBy = "bookShelfItem", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    private List<MemorableSentence> memorableSentences = new ArrayList<>();

    public BookShelfItem(BookDto bookDto, BookShelf bookShelf) {
        this.isbn = bookDto.getIsbn();
        this.title = bookDto.getTitle();
        this.author = String.join(", ", bookDto.getAuthors());
        this.thumbnail = bookDto.getThumbnail();
        this.bookShelf = bookShelf; // shelfId 대신 bookShelf 객체 자체를 저장
        this.state = ReadState.WANT_TO_READ; // 읽기 전
        this.currentPage = 0;
        this.totalPage = 300; // 임시값 ->  사용자에게 입력받을 것
        this.createdAt = LocalDateTime.now();
    }

    public void updateProgress(Integer newCurrentPage, Integer newTotalPage) {
        if (newTotalPage != null) {
            this.totalPage = newTotalPage;
        }
        if (newCurrentPage != null) {
            this.currentPage = newCurrentPage;
        }
        if (this.currentPage > 0 && this.currentPage < this.totalPage) {
            this.state = ReadState.READING;
        } else if (this.currentPage >= this.totalPage) {
            this.currentPage = this.totalPage;
            this.state = ReadState.COMPLETED;
        } else {
            this.state = ReadState.WANT_TO_READ;
        }
    }
}

