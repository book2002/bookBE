package com.team2002.capstone.controller;

import com.team2002.capstone.dto.FollowResponseDTO;
import com.team2002.capstone.service.FollowService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RequestMapping("/api/v1/follow")
@RestController
public class FollowController {

    private final FollowService followService;

    @Operation(summary = "팔로우")
    @PostMapping("/{profileId}")
    public ResponseEntity<FollowResponseDTO> follow(@PathVariable("profileId") Long profileId) {
        FollowResponseDTO followResponseDTO = followService.follow(profileId);
        return ResponseEntity.status(HttpStatus.CREATED).body(followResponseDTO);
    }

    @Operation(summary = "언팔로우")
    @DeleteMapping("/{profileId}")
    public ResponseEntity<Void> unFollow(@PathVariable("profileId") Long profileId) {
        followService.unfollow(profileId);
        return ResponseEntity.ok().build();
    }
}
