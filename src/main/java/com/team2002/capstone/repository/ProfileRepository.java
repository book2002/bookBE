package com.team2002.capstone.repository;

import com.team2002.capstone.domain.Member;
import com.team2002.capstone.domain.Profile;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ProfileRepository extends JpaRepository<Profile, Long> {

    Optional<Profile> findByMember(Member member);
    Optional<Profile> findById(Long id);
}
