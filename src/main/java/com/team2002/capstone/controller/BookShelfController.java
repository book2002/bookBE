package com.team2002.capstone.controller;

import com.team2002.capstone.domain.BookShelfItem;
import com.team2002.capstone.dto.BookDto;
import com.team2002.capstone.service.BookService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import com.team2002.capstone.dto.BookShelfItemDto;

@RestController
@RequestMapping("/api/v1/my-shelf")
public class BookShelfController {

    private final BookService bookService;

    public BookShelfController(BookService bookService) {
        this.bookService = bookService;
    }

    @PostMapping("/items") // 내 책장에 책 저장
    public ResponseEntity<?> saveItemToMyShelf(@RequestBody BookDto bookDto) {
        try {
            BookShelfItem savedItem = bookService.saveBookToMyShelf(bookDto);
            return ResponseEntity.ok(savedItem);
        } catch (IllegalStateException e) {
            // 중복 저장 시 409 Conflict 에러 반환
            return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
        }
    }

    @GetMapping("/items")
    public ResponseEntity<List<BookShelfItemDto>> getMyShelfItems() {
        List<BookShelfItemDto> items = bookService.getMyShelfItems();
        return ResponseEntity.ok(items);
    }

    @DeleteMapping("/items/{itemId}") // 책 삭제
    public ResponseEntity<Void> deleteItemFromMyShelf(@PathVariable Long itemId) {
        bookService.deleteBookFromMyShelf(itemId);
        return ResponseEntity.noContent().build(); // 성공 시 204 No Content 반환
    }
}
