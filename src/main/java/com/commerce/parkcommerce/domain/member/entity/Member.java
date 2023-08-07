package com.commerce.parkcommerce.domain.member.entity;

import com.commerce.parkcommerce.common.BaseEntity;
import com.commerce.parkcommerce.domain.member.Role;
import com.commerce.parkcommerce.domain.member.dto.CreateMemberDto;
import com.commerce.parkcommerce.domain.order.Orders;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import static com.commerce.parkcommerce.domain.member.Role.ADMIN;
import static jakarta.persistence.GenerationType.IDENTITY;
import static lombok.AccessLevel.PROTECTED;

@Entity
@AllArgsConstructor
@NoArgsConstructor(access = PROTECTED)
@SuperBuilder(toBuilder = true)
@Getter
public class Member extends BaseEntity {

    private String name;
    private String password;
    private String email;
    private String providerType;
    @Enumerated(EnumType.STRING)
    private Role role;
    @Embedded
    private Address address;

    @OneToMany(mappedBy = "member")
    @Builder.Default
    private List<Orders> orders = new ArrayList<>();

    public static Member createMember(CreateMemberDto dto) {
        return Member.builder()
                .name(dto.name())
                .password(dto.password())
                .email(dto.email())
                .address(
        new Address(dto.city(), dto.street(), dto.zipcode()))
                .role(Role.MEMBER)
                .build();
    }

    public Collection<? extends GrantedAuthority> grantedAuthorities() {
        List<GrantedAuthority> grantedAuthorities = new ArrayList<>();
        grantedAuthorities.add(new SimpleGrantedAuthority(role.name()));
        if (isAdmin()) grantedAuthorities.add(new SimpleGrantedAuthority(ADMIN.name()));
        return grantedAuthorities;
    }

    private boolean isAdmin() {
        return role.equals(ADMIN);
    }
}
