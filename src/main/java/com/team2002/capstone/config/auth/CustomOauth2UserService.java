package com.team2002.capstone.config.auth;

import com.team2002.capstone.domain.Member;
import com.team2002.capstone.domain.enums.AccountStatusEnum;
import com.team2002.capstone.domain.enums.ProviderEnum;
import com.team2002.capstone.domain.enums.RoleEnum;
import com.team2002.capstone.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class CustomOauth2UserService extends DefaultOAuth2UserService {

    private final MemberRepository memberRepository;

    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
        OAuth2User oAuth2User = super.loadUser(userRequest);
        log.info("getAttributes : {}", oAuth2User.getAttributes());

        String provider = userRequest.getClientRegistration().getRegistrationId();

        if (provider.equals("google")) {
            log.info("구글 로그인");
            GoogleUserDetails googleUserDetails = new GoogleUserDetails(oAuth2User.getAttributes());

            String googleId = googleUserDetails.getProviderId();
            String email = googleUserDetails.getEmail();
            String name = googleUserDetails.getName();

            Optional<Member> findMember = memberRepository.findByEmail(email);
            Member member;

            if (findMember.isEmpty()) {
                member = Member.builder()
                        .email(email)
                        .password(null)
                        .name(name)
                        .provider(ProviderEnum.GOOGLE)
                        .googleId(googleId)
                        .role(RoleEnum.USER)
                        .status(AccountStatusEnum.ACTIVE)
                        .build();
                memberRepository.save(member);
            } else {
                member = findMember.get();
            }

            return new CustomOauth2UserDetails(member, oAuth2User.getAttributes());
        }

        throw new OAuth2AuthenticationException("Unsupported provider: " + provider);
    }
}