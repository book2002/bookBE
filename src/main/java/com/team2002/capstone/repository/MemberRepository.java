package com.team2002.capstone.repository;

import com.team2002.capstone.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, Long> {

    Optional<Member> findByEmail(String email);
    Optional<Member> findByGoogleId(String googleId);
    Optional<Member> findByFcmToken(String fcmToken);
}
