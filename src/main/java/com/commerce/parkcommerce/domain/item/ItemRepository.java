package com.commerce.parkcommerce.domain.item;

import com.commerce.parkcommerce.domain.item.entity.Item;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ItemRepository extends JpaRepository<Item, Long> {
}
