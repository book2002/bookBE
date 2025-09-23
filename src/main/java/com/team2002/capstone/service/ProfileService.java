package com.team2002.capstone.service;

import com.team2002.capstone.domain.Member;
import com.team2002.capstone.domain.Profile;
import com.team2002.capstone.domain.enums.GenderEnum;
import com.team2002.capstone.dto.ProfileRequestDTO;
import com.team2002.capstone.dto.ProfileResponseDTO;
import com.team2002.capstone.dto.ProfileUpdateRequestDTO;
import com.team2002.capstone.repository.MemberRepository;
import com.team2002.capstone.repository.ProfileRepository;
import com.team2002.capstone.util.SecurityUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class ProfileService {

    private final ProfileRepository profileRepository;
    private final MemberRepository memberRepository;

    @Transactional
    public ProfileResponseDTO createProfile(ProfileRequestDTO requestDTO) {
        // 현재 로그인된 사용자 정보
        String userEmail = SecurityUtil.getCurrentUsername();
        Member member = memberRepository.findByEmail(userEmail)
                .orElseThrow(() -> new RuntimeException("회원을 찾을 수 없습니다."));

        if (profileRepository.findByMember(member).isPresent()) {
            throw new IllegalStateException("이미 프로필을 생성했습니다.");
        }

        GenderEnum gender = requestDTO.getGender().equals("MALE") ? GenderEnum.MALE : GenderEnum.FEMALE;

        Profile profile = Profile.builder()
                .nickname(requestDTO.getNickname())
                .birth(requestDTO.getBirth())
                .gender(gender)
                .bio(requestDTO.getBio())
                .member(member)
                .build();

        profileRepository.save(profile);
        return ProfileResponseDTO.builder()
                .profileId(profile.getId())
                .nickName(profile.getNickname())
                .createdAt(profile.getCreated_at())
                .build();
    }

    @Transactional
    public void updateProfile(ProfileUpdateRequestDTO requestDTO) {
        String userEmail = SecurityUtil.getCurrentUsername();

        Member member = memberRepository.findByEmail(userEmail)
                .orElseThrow(() -> new RuntimeException("회원을 찾을 수 없습니다."));
        Profile profile = profileRepository.findByMember(member)
                .orElseThrow(() -> new RuntimeException("프로필을 찾을 수 없습니다."));

        if(requestDTO.getNickname() != null && !requestDTO.getNickname().isBlank()) {
            profile.setNickname(requestDTO.getNickname());
        }
        if(requestDTO.getBio() != null && !requestDTO.getBio().isBlank()) {
            profile.setBio(requestDTO.getBio());
        }
    }
}
