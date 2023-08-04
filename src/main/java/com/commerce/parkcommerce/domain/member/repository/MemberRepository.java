package com.commerce.parkcommerce.domain.member.repository;

import com.commerce.parkcommerce.domain.member.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member, Long> {
}
