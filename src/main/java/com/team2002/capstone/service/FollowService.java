package com.team2002.capstone.service;

import com.team2002.capstone.domain.Follow;
import com.team2002.capstone.domain.Member;
import com.team2002.capstone.domain.Profile;
import com.team2002.capstone.dto.FollowResponseDTO;
import com.team2002.capstone.exception.ResourceNotFoundException;
import com.team2002.capstone.repository.FollowRepository;
import com.team2002.capstone.repository.MemberRepository;
import com.team2002.capstone.repository.ProfileRepository;
import com.team2002.capstone.util.SecurityUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class FollowService {

    private final FollowRepository followRepository;
    private final ProfileRepository profileRepository;
    private final MemberRepository memberRepository;

    private Profile getCurrentProfile() {
        String userEmail = SecurityUtil.getCurrentUsername();
        Member member = memberRepository.findByEmail(userEmail)
                .orElseThrow(() -> new ResourceNotFoundException("회원을 찾을 수 없습니다."));
        return profileRepository.findByMember(member)
                .orElseThrow(() -> new ResourceNotFoundException("현재 로그인한 사용자의 프로필을 찾을 수 없습니다."));
    }

    @Transactional
    public FollowResponseDTO follow(Long followingId) {
        Profile follower = getCurrentProfile();
        Profile following = profileRepository.findById(followingId)
                .orElseThrow(() -> new ResourceNotFoundException("팔로우 대상 프로필을 찾을 수 없습니다."));

        if (followRepository.findByFollowerAndFollowing(follower, following).isPresent()) {
            throw new IllegalStateException("이미 팔로우하고 있는 프로필입니다.");
        }

        Follow follow = Follow.builder()
                .follower(follower)
                .following(following)
                .build();
        followRepository.save(follow);

        return FollowResponseDTO.builder()
                .id(follow.getId())
                .followerName(follower.getNickname())
                .followingName(following.getNickname())
                .build();
    }

    @Transactional
    public void unfollow(Long followingId) {
        Profile follower = getCurrentProfile();

        Profile following = profileRepository.findById(followingId)
                .orElseThrow(() -> new ResourceNotFoundException("언팔로우 대상 프로필을 찾을 수 없습니다."));

        followRepository.findByFollowerAndFollowing(follower, following)
                .orElseThrow(() -> new IllegalStateException("팔로우하지 않은 관계입니다."));

        followRepository.deleteByFollowerAndFollowing(follower, following);
    }
}
