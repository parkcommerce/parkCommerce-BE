package com.commerce.parkcommerce.domain.order;

import com.commerce.parkcommerce.domain.member.MemberService;
import com.commerce.parkcommerce.domain.member.entity.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class OrderService {
    private final OrdersRepository ordersRepository;
    private final MemberService memberService;

    @Transactional
    public void createOrder(Long memberId) {
        Member member = memberService.getMember(memberId);
        Orders order = Orders.createOrders(member);
        ordersRepository.save(order);
    }

}
