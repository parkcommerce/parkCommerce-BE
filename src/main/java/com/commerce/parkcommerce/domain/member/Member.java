package com.commerce.parkcommerce.domain.member;

import com.commerce.parkcommerce.domain.member.dto.CreateMemberDto;
import com.commerce.parkcommerce.domain.order.Order;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

import static jakarta.persistence.GenerationType.IDENTITY;
import static lombok.AccessLevel.PROTECTED;

@Entity
@AllArgsConstructor
@NoArgsConstructor(access = PROTECTED)
@Builder(toBuilder = true)
public class Member {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    private long id;

    private String name;

    @Embedded
    private Address address;

    @OneToMany(mappedBy = "member")
    @Builder.Default
    private List<Order> orders = new ArrayList<>();

    static Member createMember(CreateMemberDto dto) {
        return Member.builder()
                .name(dto.name())
                .address(
        new Address(dto.city(), dto.street(), dto.zipcode()))
                .build();
    }
}
