package com.commerce.parkcommerce.domain.order;

import com.commerce.parkcommerce.common.BaseEntity;
import com.commerce.parkcommerce.domain.member.entity.Member;
import com.commerce.parkcommerce.domain.orderItem.OrderItem;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.util.ArrayList;
import java.util.List;

import static jakarta.persistence.FetchType.LAZY;
import static lombok.AccessLevel.PROTECTED;

@Entity
@AllArgsConstructor
@NoArgsConstructor(access = PROTECTED)
@SuperBuilder(toBuilder = true)
@Getter
public class Orders extends BaseEntity {

    @Enumerated(EnumType.STRING)
    private Status status;
    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @OneToMany(mappedBy = "order")
    @Builder.Default
    private List<OrderItem> orderItems = new ArrayList<>();

    public static Orders createOrders(Member member) {
        Orders order = Orders.builder()
                .status(Status.ORDER)
                .member(member)
                .build();
        member.getOrders().add(order);
        return order;
    }

}
