package com.commerce.parkcommerce.global.security.oauth2;


import com.commerce.parkcommerce.domain.member.entity.Member;
import com.commerce.parkcommerce.domain.member.MemberService;
import com.commerce.parkcommerce.global.security.oauth2.converter.ProviderUserRequest;
import com.commerce.parkcommerce.global.security.oauth2.service.AbstractOAuth2UserService;
import com.commerce.parkcommerce.global.security.oauth2.user.ProviderUser;
import lombok.RequiredArgsConstructor;
import org.springframework.security.oauth2.client.registration.ClientRegistration;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserService;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Map;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class CustomOAuth2UserService extends AbstractOAuth2UserService implements OAuth2UserService<OAuth2UserRequest, OAuth2User> {
    private final MemberService memberService;

    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
//        OAuth2User oAuth2User = super.loadUser(userRequest);
//        String oauthId = oAuth2User.getName();
//        String providerType = userRequest.getClientRegistration().getRegistrationId().toUpperCase();
//        String username = providerType + "%s".formatted(oauthId);
//        Map<String, Object> userAttributes = oAuth2User.getAttributes();

        //추가
        ClientRegistration clientRegistration = userRequest.getClientRegistration();
        OAuth2UserService<OAuth2UserRequest, OAuth2User> oAuth2UserService = new DefaultOAuth2UserService();
        OAuth2User oAuth2User = oAuth2UserService.loadUser(userRequest); // social login 정보 꺼낼수있음
        //
        String registrationId = userRequest.getClientRegistration().getRegistrationId();

        //
        ProviderUserRequest providerUserRequest = new ProviderUserRequest(clientRegistration,oAuth2User);
        ProviderUser providerUser = providerUser(providerUserRequest);

        // 본인인증 체크
        // 기본은 본인인증을 하지 않은 상태임
        selfCertificate(providerUser);
        memberService.socialJoin(providerUser.getProvider(), providerUser.getUsername(), providerUser.getEmail());
        super.register(providerUser, userRequest);

        return new PrincipalUser(providerUser);
        // 추가 끝
//        String email = null;
//        if (userAttributes.containsKey("kakao")) {
//            Map<String , Object> kakaoAccount = (Map<String, Object>) userAttributes.get("kakao_account");
//            if (kakaoAccount.containsKey("email")) {
//                email = (String) kakaoAccount.get("email");
//            }
//        }
//        Member member = memberService.socialLogin(providerType, username, email);
//        return new CustomOAuth2User(member.getName(), member.getPassword(), member.grantedAuthorities());
    }
}
