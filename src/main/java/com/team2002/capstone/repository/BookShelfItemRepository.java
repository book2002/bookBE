package com.team2002.capstone.repository;

import com.team2002.capstone.domain.BookShelf; // BookShelf import
import com.team2002.capstone.domain.BookShelfItem;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import java.util.Optional;

public interface BookShelfItemRepository extends JpaRepository<BookShelfItem, Long> {
    List<BookShelfItem> findByBookShelf(BookShelf bookShelf); // shelfId 대신 bookShelf 객체로 찾기

    // Isbn과 BookShelf 객체로 중복 확인
    Optional<BookShelfItem> findByIsbnAndBookShelf(String isbn, BookShelf bookShelf);
}