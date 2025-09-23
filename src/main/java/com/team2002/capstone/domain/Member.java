package com.team2002.capstone.domain;

import com.team2002.capstone.domain.common.BaseEntity;
import com.team2002.capstone.domain.enums.AccountStatusEnum;
import com.team2002.capstone.domain.enums.ProviderEnum;
import com.team2002.capstone.domain.enums.RoleEnum;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@Builder
@ToString
@AllArgsConstructor
public class Member extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String email;

    private String password;

    private String name;

    @Enumerated(EnumType.STRING)
    private ProviderEnum provider;

    private String googleId;

    @Enumerated(EnumType.STRING)
    private AccountStatusEnum status;

    @Enumerated(EnumType.STRING)
    private RoleEnum role;

    @OneToOne(mappedBy = "member", fetch = FetchType.LAZY)
    private Profile profile;

    public Member() {
    }
}
