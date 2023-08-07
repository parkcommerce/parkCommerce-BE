package com.commerce.parkcommerce.global.security;

import com.commerce.parkcommerce.domain.member.entity.Member;
import com.commerce.parkcommerce.domain.member.repository.MemberRepository;
import com.commerce.parkcommerce.global.error.exception.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomDetailService implements UserDetailsService {

    private final MemberRepository memberRepository;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Member member = memberRepository.findByName(username).orElseThrow(
                () -> new NotFoundException("username(%s) not found".formatted(username)));
        return new User(member.getName(), member.getPassword(), member.grantedAuthorities());
    }
}