//package com.commerce.parkcommerce.global.common;
//
//import com.commerce.parkcommerce.domain.member.MemberService;
//import com.commerce.parkcommerce.domain.member.entity.Member;
//import com.commerce.parkcommerce.global.util.CookieUtil;
//import jakarta.servlet.http.Cookie;
//import jakarta.servlet.http.HttpServletRequest;
//import jakarta.servlet.http.HttpServletResponse;
//import org.springframework.security.core.context.SecurityContext;
//import org.springframework.security.core.context.SecurityContextHolder;
//import org.springframework.security.core.userdetails.User;
//import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
//import org.springframework.stereotype.Component;
//
//import java.util.Date;
//import java.util.Map;
//
//@Component
//public class Rq {
//    private final CookieUtil cookieUt;
//    private final MemberService memberService;
//    private final HttpServletRequest req;
//    private final HttpServletResponse resp;
//    private final User user;
//    private Member member = null;
//
//    public Rq(CookieUtil cookieUt, MemberService memberService, HttpServletRequest req, HttpServletResponse resp) {
//        this.cookieUt = cookieUt;
//        this.memberService = memberService;
//        this.req = req;
//        this.resp = resp;
//
//        SecurityContext context = SecurityContextHolder.getContext();
//        Object principal = context.getAuthentication().getPrincipal();
//
//        if (principal instanceof User) {
//            this.user = (User) principal;
//        } else {
//            this.user = null;
//        }
//    }
//
//    // 로그인 되어 있는지 체크
//    public boolean isLogin() {
//        return user != null;
//    }
//
//    // 로그아웃 되어 있는지 체크
//    public boolean isLogout() {
//        return !isLogin();
//    }
//
//    // 로그인 된 회원의 객체
//    public Member getMember() {
//        if (isLogout()) return null;
//
//        // 데이터가 없는지 체크
//        if (member == null) {
//            member = memberService.findByUsername(user.getUsername());
//        }
//
//        return member;
//    }
//
//    // 뒤로가기 + 메세지
//    public String historyBack(String msg) {
//        String referer = req.getHeader("referer");
//        String key = "historyBackErrorMsg___" + referer;
//        req.setAttribute("localStorageKeyAboutHistoryBackErrorMsg", key);
//        req.setAttribute("historyBackErrorMsg", msg);
//        // 200 이 아니라 400 으로 응답코드가 지정되도록
//        resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
//        return "common/js";
//    }
//
//    // 뒤로가기 + 메세지
//    public String historyBack(RsData rsData) {
//        return historyBack(rsData.getMsg());
//    }
//
//
//
//    public Cookie getCookie(String cookieName) {
//        return cookieUt.getCookie(req, cookieName);
//    }
//
//    public void setCookie(String cookieName, String value) {
//        if (!value.contains("/member/join")) {
//            Cookie cookie = cookieUt.createCookie(cookieName, value);
//            resp.addCookie(cookie);
//        }
//    }
//
//    public void setRefreshCookie(String cookieName, String value) {
//        Cookie cookie = cookieUt.createRefreshCookie(cookieName, value);
//        resp.addCookie(cookie);
//    }
//
//    public void expireCookie(String cookieName) {
//        Cookie cookie = getCookie(cookieName);
//        cookie = cookieUt.expireCookie(cookie);
//        resp.addCookie(cookie);
//    }
//
//    public String getReferer() {
//        String referer = req.getHeader("referer");
//
//        if (referer != null) {
//            int queryIndex = referer.indexOf("?");
//
//            if (queryIndex != -1) {
//                referer = referer.substring(0, queryIndex);
//            }
//        }
//
//        return referer;
//    }
//
//    public String getPreviousUrl(Cookie cookie) {
//        String preUrl = cookie.getValue();
//        expireCookie(cookie.getName());
//
//        return preUrl;
//    }
//
//    // 쿠키삭제 및 로그아웃 처리
//    public void logout(long id) {
////        redisUt.delete(String.valueOf(id));
//
////        expireCookie(ACCESS_TOKEN.value());
////        expireCookie(REFRESH_TOKEN.value());
//
//        SecurityContext context = SecurityContextHolder.getContext();
//
//        SecurityContextLogoutHandler logoutHandler = new SecurityContextLogoutHandler();
//        logoutHandler.logout(req, resp, context.getAuthentication());
//    }
//}
