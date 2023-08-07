package com.commerce.parkcommerce.global.security.oauth2;

public class OAuth2Config {
    public enum SocialType{
        //        GOOGLE("google"),
//        APPLE("apple"),
//        FACEBOOK("facebook"),
//        NAVER("naver"),
        KAKAO("kakao");
        private final String socialName;

        private SocialType(String socialName) {
            this.socialName = socialName;
        }

        public String getSocialName() {
            return socialName;
        }
    }
}
