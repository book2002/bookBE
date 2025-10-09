package com.team2002.capstone.domain;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.List;

@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
public class ReadingHabit {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id", nullable = false)
    private Member member;

    private String targetTime;
    private String recurringDays; // "MON,TUE" 처럼 쉼표로 구분해서 저장
    private boolean isActive;
    private LocalDate lastNotifiedDate;

    public void updateLastNotifiedDate(LocalDate date) {
        this.lastNotifiedDate = date;
    }

    public void toRecurringDays(List<String> days) {
        if (recurringDays != null && !recurringDays.isEmpty()) {
            this.recurringDays = String.join(",", days);
        } else {
            this.recurringDays = "";
        }
    }

    public ReadingHabit() {
    }
}
