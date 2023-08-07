package com.commerce.parkcommerce.domain.orderItem;

import com.commerce.parkcommerce.common.BaseEntity;
import com.commerce.parkcommerce.domain.item.entity.Item;
import com.commerce.parkcommerce.domain.order.Orders;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import static lombok.AccessLevel.PROTECTED;

@Entity
@AllArgsConstructor
@NoArgsConstructor(access = PROTECTED)
@SuperBuilder(toBuilder = true)
@Getter
public class OrderItem extends BaseEntity {


    @ManyToOne
    @JoinColumn(name = "order_id")
    private Orders order;

    @ManyToOne
    @JoinColumn(name = "item_id")
    private Item item;

    public static OrderItem createOrderItem(Orders order, Item item) {
        OrderItem orderItem = OrderItem.builder()
                .order(order)
                .item(item)
                .build();
        order.getOrderItems().add(orderItem);
        item.getOrderItems().add(orderItem);
        return orderItem;
    }
}
