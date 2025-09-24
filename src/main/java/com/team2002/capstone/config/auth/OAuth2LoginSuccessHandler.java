package com.team2002.capstone.config.auth;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.team2002.capstone.config.jwt.JwtTokenProvider;
import com.team2002.capstone.domain.Member;
import com.team2002.capstone.dto.JwtTokenDTO;
import com.team2002.capstone.dto.LoginResponseDTO;
import com.team2002.capstone.repository.ProfileRepository;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
@RequiredArgsConstructor
public class OAuth2LoginSuccessHandler implements AuthenticationSuccessHandler {

    private final ProfileRepository profileRepository;
    private final JwtTokenProvider jwtTokenProvider;
    private final ObjectMapper objectMapper;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        CustomOauth2UserDetails userDetails = (CustomOauth2UserDetails) authentication.getPrincipal();
        Member member = userDetails.getMember();

        boolean isNewUser = profileRepository.findByMember(member).isEmpty();

        JwtTokenDTO jwtTokenDTO = jwtTokenProvider.generateToken(member, isNewUser);
        LoginResponseDTO loginResponseDTO = LoginResponseDTO.builder()
                .grantType(jwtTokenDTO.getGrantType())
                .accessToken(jwtTokenDTO.getAccessToken())
                .refreshToken(jwtTokenDTO.getRefreshToken())
                .isNewUser(isNewUser).build();

        response.setContentType("application/json;charset=UTF-8");
        response.getWriter().write(objectMapper.writeValueAsString(loginResponseDTO));
    }
}
