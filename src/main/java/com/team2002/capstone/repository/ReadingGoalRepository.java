package com.team2002.capstone.repository;

import com.team2002.capstone.domain.Profile;
import com.team2002.capstone.domain.ReadingGoal;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ReadingGoalRepository extends JpaRepository<ReadingGoal, Long> {
    Optional<ReadingGoal> findByProfileAndTargetYear(Profile profile, int year);
}
