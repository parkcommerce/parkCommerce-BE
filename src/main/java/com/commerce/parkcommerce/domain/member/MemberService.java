package com.commerce.parkcommerce.domain.member;

import com.commerce.parkcommerce.domain.member.dto.CreateMemberDto;
import com.commerce.parkcommerce.domain.member.entity.Member;
import com.commerce.parkcommerce.domain.member.repository.MemberRepository;
import com.commerce.parkcommerce.global.error.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MemberService {
    private final MemberRepository memberRepository;


    public Member createMember(CreateMemberDto dto) {
        Member member = Member.createMember(dto);
        memberRepository.save(member);
        return member;
    }

    public Member getMember(Long id) {
        return memberRepository.findById(id).orElseThrow(() -> new NotFoundException("id를 확인해주세요"));
    }

    public Member socialLogin(String providerType, String username, String email) {
        CreateMemberDto dto = new CreateMemberDto(username,"" , providerType,email,"서울", "강남구", "12345");
        return createMember(dto);
    }
}
