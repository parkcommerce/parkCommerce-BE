package com.commerce.parkcommerce.global.error;

import lombok.Getter;

@Getter
public enum ErrorStatus {
    ITEM_NOT_FOUND(404, "ITEM_NOT_FOUND"),
    MEMBER_NOT_FOUND(404, "MEMBER_NOT_FOUND"),
    ORDER_NOT_FOUND(404, "ORDER_NOT_FOUND");

    private final int status;
    private final String message;

    ErrorStatus(int status, String message) {
        this.status = status;
        this.message = message;
    }
}
