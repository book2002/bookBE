package com.team2002.capstone.domain;

import com.team2002.capstone.domain.common.BaseEntity;
import com.team2002.capstone.domain.enums.GenderEnum;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@Builder
@ToString
@AllArgsConstructor
public class Profile extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String nickname;

    private String bio;

    @Enumerated(EnumType.STRING)
    private GenderEnum gender;

    private String birth;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id", nullable = false)
    private Member member;

    public Profile() {
    }
}
