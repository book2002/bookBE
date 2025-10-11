package com.team2002.capstone.controller;

import com.team2002.capstone.dto.*;
import com.team2002.capstone.service.MemberService;
import com.team2002.capstone.util.SecurityUtil;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RequestMapping("/api/v1/member")
@RestController
public class MemberController {

    private final MemberService memberService;
    private final PasswordEncoder passwordEncoder;

    @Operation(summary = "회원가입")
    @PostMapping("/signup")
    public ResponseEntity<MemberResponseDTO> signUp(@Validated @RequestBody MemberRequestDTO memberRequestDTO) {
        MemberResponseDTO memberResponseDTO = memberService.save(memberRequestDTO.toEntity(memberRequestDTO, passwordEncoder));
        return ResponseEntity.status(HttpStatus.CREATED).body(memberResponseDTO);
    }

    @Operation(summary = "일반 로그인")
    @PostMapping("/login")
    public ResponseEntity<LoginResponseDTO> login(@Validated @RequestBody LoginRequestDTO loginRequestDTO) {
        LoginResponseDTO loginResponseDTO = memberService.login(loginRequestDTO);
        return ResponseEntity.status(HttpStatus.OK).body(loginResponseDTO);
    }

    @Operation(summary = "비밀번호 변경")
    @PutMapping("/password")
    public ResponseEntity<Void> updatePassword(@Validated @RequestBody MemberUpdateRequestDTO memberUpdateRequestDTO){
        memberService.updatePassword(memberUpdateRequestDTO);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @Operation(summary = "계정 비활성화")
    @PutMapping("/inactivate")
    public ResponseEntity<Void> inactivateMember() {
        memberService.inactivateMember();
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @Operation(summary = "회원 탈퇴")
    @DeleteMapping("/delete")
    public ResponseEntity<Void> deleteMember() {
        memberService.deleteMember();
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @Operation(summary = "FCM 토큰 초기 저장 및 갱신")
    @PutMapping("/fcm-token")
    public ResponseEntity<Void> updateFcmToken(@Validated @RequestBody FcmTokenRequestDTO fcmTokenRequestDTO) {
        memberService.updateFcmToken(fcmTokenRequestDTO);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @PostMapping("/test")
    @ResponseBody
    public String test() {
        return SecurityUtil.getCurrentUsername();
    }
}
