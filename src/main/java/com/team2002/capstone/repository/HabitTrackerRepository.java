package com.team2002.capstone.repository;

import com.team2002.capstone.domain.HabitTracker;
import com.team2002.capstone.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface HabitTrackerRepository extends JpaRepository<HabitTracker, Long> {
    Optional<HabitTracker> findByMember(Member member);
    Optional<HabitTracker> findByMemberAndRecordDate(Member member, LocalDate recordDate);
    List<HabitTracker> findAllByMemberAndRecordDateBetween(Member member, LocalDate startDate, LocalDate endDate);

    @Query("SELECT m.id FROM Member m " +
            "WHERE m.isHabitTrackerActive = true " +
            "  AND m.id NOT IN (" +
            "    SELECT ht.member.id FROM HabitTracker ht WHERE ht.recordDate = :date" +
            ")")
    List<Long> findMemberIdsWithoutRecordForToday(@Param("date") LocalDate date);
}
