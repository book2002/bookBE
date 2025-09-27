package com.team2002.capstone.service;

import com.team2002.capstone.domain.Follow;
import com.team2002.capstone.domain.Member;
import com.team2002.capstone.domain.Profile;
import com.team2002.capstone.domain.enums.GenderEnum;
import com.team2002.capstone.dto.*;
import com.team2002.capstone.repository.FollowRepository;
import com.team2002.capstone.repository.MemberRepository;
import com.team2002.capstone.repository.ProfileRepository;
import com.team2002.capstone.util.SecurityUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class ProfileService {

    private final ProfileRepository profileRepository;
    private final MemberRepository memberRepository;
    private final FollowRepository followRepository;

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
                .followings(new HashSet<>())
                .followers(new HashSet<>())
                .build();

        profileRepository.save(profile);
        return ProfileResponseDTO.builder()
                .profileId(profile.getId())
                .nickName(profile.getNickname())
                .bio(profile.getBio())
                .followerCount(profile.getFollowers().size())
                .followingCount(profile.getFollowings().size())
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

    @Transactional
    public ProfileViewResponseDTO getProfile(Long profileId) {
        String userEmail = SecurityUtil.getCurrentUsername();
        Member member = memberRepository.findByEmail(userEmail)
                .orElseThrow(() -> new RuntimeException("회원을 찾을 수 없습니다."));
        Profile currentProfile = profileRepository.findByMember(member)
                .orElseThrow(() -> new RuntimeException("현재 로그인한 사용자의 프로필을 찾을 수 없습니다."));

        Profile viewProfile = profileRepository.findById(profileId)
                .orElseThrow(() -> new RuntimeException("프로필을 찾을 수 없습니다.")); // 조회 대상

        boolean isMyProfile = false;
        boolean isFollowing = false;

        if (currentProfile != null) {
            isMyProfile = viewProfile.getId() == currentProfile.getId();
            if (!isMyProfile) { // 내 프로필이 아닌 경우에만 팔로우 여부 확인
                isFollowing = followRepository.findByFollowerAndFollowing(currentProfile, viewProfile).isPresent();
            }
        }

        int followerCount = viewProfile.getFollowers().size();
        int followingCount = viewProfile.getFollowings().size();

        return ProfileViewResponseDTO.builder()
                .nickname(viewProfile.getNickname())
                .bio(viewProfile.getBio())
                .followerCount(followerCount)
                .followingCount(followingCount)
                .isMyProfile(isMyProfile)
                .isFollowing(isFollowing)
                .build();

    }

    @Transactional
    public List<ProfileListResponseDTO> getFollowingList(){
        Profile currentProfile = getCurrentProfile();

        List<Follow> followings = followRepository.findByFollower(currentProfile);

        return followings.stream()
                .map(follow -> {
                    Profile followingProfile = follow.getFollowing();
                    return ProfileListResponseDTO.builder()
                            .profileId(followingProfile.getId())
                            .nickname(followingProfile.getNickname())
                            .bio(followingProfile.getBio())
                            .isFollowing(true) // 팔로잉 목록이므로 항상 true
                            .build();
                })
                .collect(Collectors.toList());
    }

    @Transactional
    public List<ProfileListResponseDTO> getFollowerList(){
        Profile currentProfile = getCurrentProfile();

        List<Follow> followers = followRepository.findByFollowing(currentProfile);

        return followers.stream()
                .map(follow -> {
                    Profile followerProfile = follow.getFollower();
                    boolean isFollowing = followRepository.findByFollowerAndFollowing(currentProfile, followerProfile).isPresent();
                    return ProfileListResponseDTO.builder()
                            .profileId(followerProfile.getId())
                            .nickname(followerProfile.getNickname())
                            .bio(followerProfile.getBio())
                            .isFollowing(isFollowing)
                            .build();
                })
                .collect(Collectors.toList());
    }

    private Profile getCurrentProfile() {
        String userEmail = SecurityUtil.getCurrentUsername();
        Member member = memberRepository.findByEmail(userEmail)
                .orElseThrow(() -> new RuntimeException("회원을 찾을 수 없습니다."));
        return profileRepository.findByMember(member)
                .orElseThrow(() -> new RuntimeException("현재 로그인한 사용자의 프로필을 찾을 수 없습니다."));
    }
}
