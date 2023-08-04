package com.commerce.parkcommerce.domain.member.dto;

public record CreateMemberDto(
        String name,
        String city,
        String street,
        String zipcode
) {
}
