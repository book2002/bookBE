package com.team2002.capstone.controller;

import com.team2002.capstone.dto.HabitStatusUpdateRequestDTO;
import com.team2002.capstone.dto.HabitTrackerResponseDTO;
import com.team2002.capstone.dto.ReadingHabitRequestDTO;
import com.team2002.capstone.dto.ReadingHabitResponseDTO;
import com.team2002.capstone.service.ReadingHabitService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/reading-habit")
public class ReadingHabitController {
    private final ReadingHabitService readingHabitService;

    @Operation(summary = "새로운 독서 습관 생성")
    @PostMapping
    public ResponseEntity<ReadingHabitResponseDTO> createReadingHabit(@RequestBody @Valid ReadingHabitRequestDTO requestDTO) {
        ReadingHabitResponseDTO responseDTO = readingHabitService.createReadingHabit(requestDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(responseDTO);
    }

    @Operation(summary = "독서 습관 수정")
    @PutMapping("/{habitId}")
    public ResponseEntity<ReadingHabitResponseDTO> updateReadingHabit(
            @PathVariable Long habitId,
            @RequestBody @Valid ReadingHabitRequestDTO requestDTO
    ) {
        ReadingHabitResponseDTO responseDTO = readingHabitService.updateReadingHabit(habitId, requestDTO);
        return ResponseEntity.status(HttpStatus.OK).body(responseDTO);
    }

    @Operation(summary = "독서 습관 목록 조회")
    @GetMapping
    public ResponseEntity<List<ReadingHabitResponseDTO>> getReadingHabits() {
        List<ReadingHabitResponseDTO> responseList = readingHabitService.getReadingHabits();
        return ResponseEntity.status(HttpStatus.OK).body(responseList);
    }

    @Operation(summary = "특정 독서 습관 조회")
    @GetMapping("/{habitId}")
    public ResponseEntity<ReadingHabitResponseDTO> getReadingHabit(@PathVariable Long habitId) {
        ReadingHabitResponseDTO responseDTO = readingHabitService.getReadingHabit(habitId);
        return ResponseEntity.status(HttpStatus.OK).body(responseDTO);
    }

    @Operation(summary = "특정 독서 습관 삭제")
    @DeleteMapping("/{habitId}")
    public ResponseEntity<Void> deleteReadingHabit(@PathVariable Long habitId) {
        readingHabitService.deleteReadingHabit(habitId);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @Operation(summary = "알림 활성화/비활성화")
    @PutMapping("/active")
    public ResponseEntity<ReadingHabitResponseDTO> updateActiveStatus(@RequestBody @Valid HabitStatusUpdateRequestDTO habitStatusUpdateRequestDTO) {
        ReadingHabitResponseDTO readingHabitResponseDTO = readingHabitService.updateHabitActiveStatus(habitStatusUpdateRequestDTO);
        return ResponseEntity.status(HttpStatus.OK).body(readingHabitResponseDTO);
    }
}
