package com.team2002.capstone.controller;

import com.team2002.capstone.dto.HabitStatusUpdateRequestDTO;
import com.team2002.capstone.dto.HabitTrackerRequestDTO;
import com.team2002.capstone.dto.HabitTrackerResponseDTO;
import com.team2002.capstone.service.HabitTrackerService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/v1/habit-tracker")
@RequiredArgsConstructor
public class HabitTrackerController {
    private final HabitTrackerService habitTrackerService;

    @Operation(summary = "독서 기록")
    @PostMapping("/record")
    public ResponseEntity<HabitTrackerResponseDTO> checkInHabit(@RequestBody @Valid HabitTrackerRequestDTO habitTrackerRequestDTO) {
        HabitTrackerResponseDTO responseDTO = habitTrackerService.record(habitTrackerRequestDTO);
        return ResponseEntity.status(HttpStatus.OK).body(responseDTO);
    }

    @Operation(summary = "독서 기록 상태 조회 (한 달 단위)")
    @GetMapping("/monthly")
    public ResponseEntity<List<LocalDate>> getMonthlyHabits(@RequestParam int year,
                                                                    @RequestParam int month) {
        List<LocalDate> records = habitTrackerService.getMonthlyHabitRecords(year, month);
        return ResponseEntity.status(HttpStatus.OK).body(records);
    }

    @Operation(summary = "오늘 독서 기록 상태 조회")
    @GetMapping("/today")
    public ResponseEntity<HabitTrackerResponseDTO> getTodayHabit() {
        HabitTrackerResponseDTO responseDTO = habitTrackerService.getTodayHabitRecord();
        return ResponseEntity.status(HttpStatus.OK).body(responseDTO);
    }

    @Operation(summary = "알림 활성화/비활성화")
    @PutMapping("/active")
    public ResponseEntity<HabitTrackerResponseDTO> updateActiveStatus(@RequestBody @Valid HabitStatusUpdateRequestDTO habitStatusUpdateRequestDTO) {
        HabitTrackerResponseDTO habitTrackerResponseDTO = habitTrackerService.updateHabitActiveStatus(habitStatusUpdateRequestDTO);
        return ResponseEntity.status(HttpStatus.OK).body(habitTrackerResponseDTO);
    }
}
