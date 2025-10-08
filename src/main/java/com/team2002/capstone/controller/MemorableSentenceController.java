package com.team2002.capstone.controller;

import com.team2002.capstone.domain.MemorableSentence;
import com.team2002.capstone.dto.MemorableSentenceDto;
import com.team2002.capstone.service.BookService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/v1/sentences") // '문장' 관련 API
public class MemorableSentenceController {

    private final BookService bookService;

    public MemorableSentenceController(BookService bookService) {
        this.bookService = bookService;
    }

    // 기억에 남는 문장 저장
    @PostMapping
    public ResponseEntity<MemorableSentence> saveSentence(@RequestBody MemorableSentenceDto dto) {
        MemorableSentence savedSentence = bookService.saveMemorableSentence(dto);
        return ResponseEntity.ok(savedSentence);
    }

    // 특정 책의 모든 문장 조회
    @GetMapping("/book/{itemId}")
    public ResponseEntity<List<MemorableSentence>> getSentencesByItemId(@PathVariable Long itemId) {
        List<MemorableSentence> sentences = bookService.getMemorableSentencesByItemId(itemId);
        return ResponseEntity.ok(sentences);
    }

    // 특정 문장 수정
    @PutMapping("/{sentenceId}")
    public ResponseEntity<MemorableSentence> updateSentence(@PathVariable Long sentenceId, @RequestBody MemorableSentenceDto dto) {
        MemorableSentence updatedSentence = bookService.updateMemorableSentence(sentenceId, dto);
        return ResponseEntity.ok(updatedSentence);
    }

    // 특정 문장 삭제
    @DeleteMapping("/{sentenceId}")
    public ResponseEntity<Void> deleteSentence(@PathVariable Long sentenceId) {
        bookService.deleteMemorableSentence(sentenceId);
        return ResponseEntity.noContent().build();
    }
}
