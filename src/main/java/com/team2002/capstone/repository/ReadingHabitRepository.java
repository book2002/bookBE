package com.team2002.capstone.repository;

import com.team2002.capstone.domain.Member;
import com.team2002.capstone.domain.ReadingHabit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface ReadingHabitRepository extends JpaRepository<ReadingHabit, Long> {

    // 이후 mysql 사용시 nativeQuery로 변경하면 성능 최적화
    @Query("SELECT rh FROM ReadingHabit rh " +
            "WHERE rh.isActive = true " +
            "  AND rh.targetTime = :time " +
            "  AND rh.recurringDays LIKE %:day%")
    List<ReadingHabit> findActiveHabitsByTimeAndDay(@Param("time")String targetTime, @Param("day")String day);
    Optional<ReadingHabit> findByIdAndMember(Long id, Member member);
    List<ReadingHabit> findAllByMember(Member member);
    Optional<ReadingHabit> findByMember(Member member);
}
