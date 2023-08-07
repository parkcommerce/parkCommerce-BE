package com.commerce.parkcommerce.global.config;

import com.commerce.parkcommerce.global.security.oauth2.CustomOAuth2UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@EnableWebSecurity
@Configuration
@RequiredArgsConstructor
public class SecurityConfig {
    private final CustomOAuth2UserService customOAuth2UserService;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        return http.authorizeHttpRequests(
                        authorize -> authorize.anyRequest().permitAll())
                .oauth2Login(oauth ->
                        oauth.loginPage("/member/index").userInfoEndpoint(
                                userInfo -> userInfo.userService(customOAuth2UserService)
                        )
                )
                .build();
    }

}
