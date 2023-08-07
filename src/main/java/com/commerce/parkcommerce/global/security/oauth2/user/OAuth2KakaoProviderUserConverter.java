package com.commerce.parkcommerce.global.security.oauth2.user;

import com.commerce.parkcommerce.global.security.oauth2.OAuth2Config;
import com.commerce.parkcommerce.global.security.oauth2.converter.ProviderUserConverter;
import com.commerce.parkcommerce.global.security.oauth2.converter.ProviderUserRequest;
import com.commerce.parkcommerce.global.security.oauth2.util.OAuth2Utils;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;

public final class OAuth2KakaoProviderUserConverter implements ProviderUserConverter<ProviderUserRequest, ProviderUser> {
    @Override
    public ProviderUser convert(ProviderUserRequest providerUserRequest) {

        if (!providerUserRequest.clientRegistration().getRegistrationId().equals(OAuth2Config.SocialType.KAKAO.getSocialName())) {
            return null;
        }

        if (providerUserRequest.oAuth2User() instanceof OidcUser) {
            return null;
        }

        return new KakaoUser(OAuth2Utils.getOtherAttributes(
                providerUserRequest.oAuth2User(), "kakao_account", "profile"),
                providerUserRequest.oAuth2User(),
                providerUserRequest.clientRegistration());
    }
}
