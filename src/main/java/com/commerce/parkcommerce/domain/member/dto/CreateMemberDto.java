package com.commerce.parkcommerce.domain.member.dto;

public record CreateMemberDto(
        String name,
        String password,
        String providerType,
        String email,
        String city,
        String street,
        String zipcode
) {
}
