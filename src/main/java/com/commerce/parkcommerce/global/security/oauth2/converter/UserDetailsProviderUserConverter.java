package com.commerce.parkcommerce.global.security.oauth2.converter;

import com.commerce.parkcommerce.global.security.oauth2.form.FormUser;
import com.commerce.parkcommerce.global.security.oauth2.user.ProviderUser;
import com.commerce.parkcommerce.global.security.oauth2.user.User;

public final class UserDetailsProviderUserConverter implements ProviderUserConverter<ProviderUserRequest, ProviderUser> {

    @Override
    public ProviderUser convert(ProviderUserRequest providerUserRequest) {

        if(providerUserRequest.user() == null){
            return null;
        }

        User user = providerUserRequest.user();
        return FormUser.builder()
                .id(user.getId())
                .username(user.getUsername())
                .password(user.getPassword())
                .authorities(user.getAuthorities())
                .email(user.getEmail())
                .provider("none")
                .build();
    }
}