package com.team2002.capstone.service;

import com.team2002.capstone.domain.Member;
import com.team2002.capstone.domain.Profile;
import com.team2002.capstone.domain.ReadingGoal;
import com.team2002.capstone.dto.ReadingGoalRequestDTO;
import com.team2002.capstone.dto.ReadingGoalResponseDTO;
import com.team2002.capstone.exception.ResourceNotFoundException;
import com.team2002.capstone.repository.MemberRepository;
import com.team2002.capstone.repository.ProfileRepository;
import com.team2002.capstone.repository.ReadingGoalRepository;
import com.team2002.capstone.util.SecurityUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Year;

@Service
@RequiredArgsConstructor
public class ReadingGoalService {
    private final ReadingGoalRepository readingGoalRepository;
    private final MemberRepository memberRepository;
    private final ProfileRepository profileRepository;

    @Transactional
    public ReadingGoalResponseDTO createOrUpdateGoal(ReadingGoalRequestDTO requestDTO) {
        Profile profile = getCurrentProfile();

        ReadingGoal goal = readingGoalRepository.findByProfileAndYear(profile, requestDTO.getYear())
                .orElse(null); // 해당 연도에 목표가 있는지 확인

        if (goal != null) {
            goal.setTargetBooks(requestDTO.getTargetBooks());
        } else {
            goal = ReadingGoal.builder()
                    .year(requestDTO.getYear())
                    .targetBooks(requestDTO.getTargetBooks())
                    .profile(profile)
                    .build();
            readingGoalRepository.save(goal);
        }

        return ReadingGoalResponseDTO.builder()
                .id(goal.getId())
                .year(goal.getYear())
                .targetBooks(goal.getTargetBooks())
                .build();
    }

    public ReadingGoalResponseDTO getCurrentYearGoal() {
        Profile profile = getCurrentProfile();
        int currentYear = Year.now().getValue();

        ReadingGoal goal = readingGoalRepository.findByProfileAndYear(profile, currentYear)
                .orElseThrow(() -> new ResourceNotFoundException("올해의 독서 목표를 찾을 수 없습니다."));

        return ReadingGoalResponseDTO.builder()
                .id(goal.getId())
                .year(goal.getYear())
                .targetBooks(goal.getTargetBooks())
                .build();
    }

    private Profile getCurrentProfile() {
        String userEmail = SecurityUtil.getCurrentUsername();
        Member member = memberRepository.findByEmail(userEmail)
                .orElseThrow(() -> new ResourceNotFoundException("회원을 찾을 수 없습니다."));
        return profileRepository.findByMember(member)
                .orElseThrow(() -> new ResourceNotFoundException("현재 로그인한 사용자의 프로필을 찾을 수 없습니다."));
    }
}
