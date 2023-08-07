package com.commerce.parkcommerce.global.security.oauth2.service;

import com.commerce.parkcommerce.domain.member.MemberService;
import com.commerce.parkcommerce.domain.member.entity.Member;
import com.commerce.parkcommerce.global.security.oauth2.SelfCertification;
import com.commerce.parkcommerce.global.security.oauth2.converter.ProviderUserConverter;
import com.commerce.parkcommerce.global.security.oauth2.converter.ProviderUserRequest;
import com.commerce.parkcommerce.global.security.oauth2.user.ProviderUser;
import com.commerce.parkcommerce.global.security.oauth2.user.User;
import com.commerce.parkcommerce.global.security.oauth2.user.UserRepository;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.client.registration.ClientRegistration;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.stereotype.Service;

@Service
@Getter
public abstract class AbstractOAuth2UserService {


    @Autowired
    private UserService userService;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private SelfCertification certification;
    @Autowired
    private MemberService memberService;

    @Autowired
    private ProviderUserConverter<ProviderUserRequest, ProviderUser> providerUserConverter;



//    @Autowired
//    private JwtTokenProvider tokenProvider;
    public void selfCertificate(ProviderUser providerUser){
        certification.checkCertification(providerUser);
    }
    public void register(ProviderUser providerUser, OAuth2UserRequest userRequest){

        User user = userRepository.findByUsername(providerUser.getUsername());

        if(user == null){
            ClientRegistration clientRegistration = userRequest.getClientRegistration();
            userService.register(clientRegistration.getRegistrationId(),providerUser);
            String profileImg = providerUser.getAttributes().getOrDefault("picture", "").toString();
            String provider = providerUser.getProvider();
            String email = providerUser.getEmail();
            String username = providerUser.getUsername();

            Member member = memberService.socialLogin(provider, username, email);

        }else{
            System.out.println("userRequest = " + userRequest);
        }
    }
    public ProviderUser providerUser(ProviderUserRequest providerUserRequest){
        return providerUserConverter.convert(providerUserRequest);
    }
}