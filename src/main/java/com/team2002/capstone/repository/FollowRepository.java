package com.team2002.capstone.repository;

import com.team2002.capstone.domain.Follow;
import com.team2002.capstone.domain.Profile;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface FollowRepository extends JpaRepository<Follow, Long> {

    Optional<Follow> findByFollowerAndFollowing(Profile follower, Profile following); // 팔로우 관계 확인
    void deleteByFollowerAndFollowing(Profile follower, Profile following);

    List<Follow> findByFollower(Profile follower); // 내가 팔로우하는 사람들 목록 조회 (팔로잉)
    List<Follow> findByFollowing(Profile following); // 나를 팔로우하는 사람들 목록 조회 (팔로워)
}
