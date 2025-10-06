package com.team2002.capstone.controller;

import com.team2002.capstone.domain.BookShelfItem;
import com.team2002.capstone.domain.Review;
import com.team2002.capstone.dto.ReviewDto;
import com.team2002.capstone.service.BookService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/reviews")
public class ReviewController {

    private final BookService bookService;

    public ReviewController(BookService bookService) {
        this.bookService = bookService;
    }

    // 독서 감상문 저장
    @PostMapping("/write")
    public ResponseEntity<Review> saveReview(@RequestBody ReviewDto reviewDto) {
        Review savedReview = bookService.saveReview(reviewDto);
        return ResponseEntity.ok(savedReview);
    }

    // 저장한 리뷰 조회 - 책 마다
    @GetMapping("/book/{itemId}") // /api/reviews/book/1, /api/reviews/book/2
    public ResponseEntity<List<Review>> getReviewsByItemId(@PathVariable Long itemId) {
        List<Review> reviews = bookService.getReviewsByItemId(itemId);
        return ResponseEntity.ok(reviews);
    }

    @DeleteMapping("/{reviewId}")
    public ResponseEntity<Void> deleteReview(@PathVariable Long reviewId) {
        bookService.deleteReview(reviewId);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{reviewId}")
    public ResponseEntity<Review> updateReview(@PathVariable Long reviewId, @RequestBody ReviewDto reviewDto) {
        Review updatedReview = bookService.updateReview(reviewId, reviewDto);
        return ResponseEntity.ok(updatedReview);
    }

}