package com.commerce.parkcommerce.domain.item.entity;

import com.commerce.parkcommerce.common.BaseEntity;
import com.commerce.parkcommerce.domain.order.Orders;
import com.commerce.parkcommerce.domain.orderItem.OrderItem;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.util.ArrayList;
import java.util.List;

import static jakarta.persistence.InheritanceType.JOINED;
import static jakarta.persistence.InheritanceType.SINGLE_TABLE;
import static lombok.AccessLevel.PROTECTED;

@Entity
@Getter
@NoArgsConstructor(access = PROTECTED)
@AllArgsConstructor
@SuperBuilder(toBuilder = true)
@Inheritance(strategy = SINGLE_TABLE)
@DiscriminatorColumn
@Table(name = "item")
public abstract class Item extends BaseEntity {

    private String name;
    private int price;
    private int stockQuantity;

    @OneToMany(mappedBy = "item")
    @Builder.Default
    private List<OrderItem> orderItems = new ArrayList<>();
}
