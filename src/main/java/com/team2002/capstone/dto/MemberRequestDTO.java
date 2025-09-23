package com.team2002.capstone.dto;

import com.team2002.capstone.domain.Member;
import com.team2002.capstone.domain.enums.AccountStatusEnum;
import com.team2002.capstone.domain.enums.ProviderEnum;
import com.team2002.capstone.domain.enums.RoleEnum;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;

@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class MemberRequestDTO {

    @NotEmpty(message = "이메일 입력은 필수 입니다.")
    @Pattern(regexp = "^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+.[A-Za-z]{2,6}$", message = "이메일 형식에 맞지 않습니다.")
    private String email;

    @NotEmpty(message = "비밀번호 입력은 필수 입니다.")
    @Size(min = 8, message = "비밀번호는 최소 8자 이상이어야 합니다.")
    private String password;

    @NotEmpty(message = "이름 입력은 필수 입니다.")
    private String name;

    public Member toEntity(MemberRequestDTO dto, PasswordEncoder passwordEncoder) {
        return Member.builder()
                .email(dto.getEmail())
                .password(passwordEncoder.encode(dto.getPassword()))
                .name(dto.getName())
                .provider(ProviderEnum.LOCAL)
                .role(RoleEnum.USER)
                .status(AccountStatusEnum.ACTIVE)
                .build();
    }
}
