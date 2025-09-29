package com.team2002.capstone.controller;

import com.team2002.capstone.dto.ReadingGoalRequestDTO;
import com.team2002.capstone.dto.ReadingGoalResponseDTO;
import com.team2002.capstone.service.ReadingGoalService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/reading-goal")
public class ReadingGoalController {

    private final ReadingGoalService readingGoalService;

    @Operation(summary = "독서 목표 설정/수정")
    @PostMapping
    public ResponseEntity<ReadingGoalResponseDTO> createOrUpdateGoal(@RequestBody @Validated ReadingGoalRequestDTO requestDTO) {
        ReadingGoalResponseDTO readingGoalResponseDTO = readingGoalService.createOrUpdateGoal(requestDTO);
        return ResponseEntity.status(HttpStatus.OK).body(readingGoalResponseDTO);
    }

    @Operation(summary = "올해 독서 목표 조회")
    @GetMapping("/current")
    public ResponseEntity<ReadingGoalResponseDTO> getCurrentGoal() {
        ReadingGoalResponseDTO readingGoalResponseDTO = readingGoalService.getCurrentYearGoal();
        return ResponseEntity.status(HttpStatus.OK).body(readingGoalResponseDTO);
    }
}
