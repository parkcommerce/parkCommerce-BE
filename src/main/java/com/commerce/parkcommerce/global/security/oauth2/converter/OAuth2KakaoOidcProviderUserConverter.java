package com.commerce.parkcommerce.global.security.oauth2.converter;

import com.commerce.parkcommerce.global.security.oauth2.OAuth2Config;
import com.commerce.parkcommerce.global.security.oauth2.oidc.KakaoOidcUser;
import com.commerce.parkcommerce.global.security.oauth2.user.ProviderUser;
import com.commerce.parkcommerce.global.security.oauth2.util.OAuth2Utils;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;

public final class OAuth2KakaoOidcProviderUserConverter implements ProviderUserConverter<ProviderUserRequest, ProviderUser> {
    @Override
    public ProviderUser convert(ProviderUserRequest providerUserRequest) {

        if (!providerUserRequest.clientRegistration().getRegistrationId().equals(OAuth2Config.SocialType.KAKAO.getSocialName())) {
            return null;
        }

        if (!(providerUserRequest.oAuth2User() instanceof OidcUser)) {
            return null;
        }

        return new KakaoOidcUser(OAuth2Utils.getMainAttributes(
                providerUserRequest.oAuth2User()),
                providerUserRequest.oAuth2User(),
                providerUserRequest.clientRegistration());
    }
}