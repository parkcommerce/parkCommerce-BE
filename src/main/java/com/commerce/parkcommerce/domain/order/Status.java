package com.commerce.parkcommerce.domain.order;

import jakarta.persistence.Enumerated;


public enum Status {
    ORDER,
    DELIVERY,
    CANCEL
}
