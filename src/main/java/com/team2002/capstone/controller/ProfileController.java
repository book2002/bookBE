package com.team2002.capstone.controller;

import com.team2002.capstone.dto.ProfileRequestDTO;
import com.team2002.capstone.dto.ProfileResponseDTO;
import com.team2002.capstone.dto.ProfileUpdateRequestDTO;
import com.team2002.capstone.service.ProfileService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RequestMapping("/api/v1/profile")
@RestController
public class ProfileController {

    private final ProfileService profileService;

    @Operation(summary = "프로필 생성 (최초 로그인)")
    @PostMapping("/create")
    public ResponseEntity<ProfileResponseDTO> createProfile(@RequestBody ProfileRequestDTO profileRequestDTO) {
        ProfileResponseDTO profileResponseDTO = profileService.createProfile(profileRequestDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(profileResponseDTO);
    }

    @Operation(summary = "프로필 수정 (닉네임, 바이오)")
    @PutMapping("/edit")
    public ResponseEntity<Void> updateProfile(@RequestBody ProfileUpdateRequestDTO profileUpdateRequestDTO) {
        profileService.updateProfile(profileUpdateRequestDTO);
        return ResponseEntity.status(HttpStatus.OK).build();
    }
}
