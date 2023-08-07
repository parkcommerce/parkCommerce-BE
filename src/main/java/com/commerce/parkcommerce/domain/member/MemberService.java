package com.commerce.parkcommerce.domain.member;

import com.commerce.parkcommerce.domain.member.dto.CreateMemberDto;
import com.commerce.parkcommerce.domain.member.entity.Member;
import com.commerce.parkcommerce.domain.member.repository.MemberRepository;
import com.commerce.parkcommerce.global.error.ErrorStatus;
import com.commerce.parkcommerce.global.error.exception.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MemberService {
    private final MemberRepository memberRepository;


    @Transactional
    public Member createMember(CreateMemberDto dto) {
        Member member = Member.createMember(dto);
        memberRepository.save(member);
        return member;
    }

    public Member getMember(Long id) {
        return memberRepository.findById(id).orElseThrow(() -> new NotFoundException("id를 확인해주세요"));
    }

    public Member getMember(String userName) {
        return memberRepository.findByName(userName).orElseThrow(
                () -> new NotFoundException(ErrorStatus.MEMBER_NOT_FOUND.getMessage()));
    }

    /**
     * 소셜로그인으로 회원가입
    * TODO: 추가 주소 입력 받아야함
     */
    public Member socialJoin(String providerType, String username, String email) {
        CreateMemberDto dto = new CreateMemberDto(username,"" , providerType,email,"", "", "");
        return createMember(dto);
    }

    /**
     * 소셜 로그인으로 로그인
     */
    public Member socialLogin(String providerType, String username, String email) {
        try {
            return getMember(username);
        } catch (NotFoundException e) {
            return socialJoin(providerType, username, email);
        }
    }


}
