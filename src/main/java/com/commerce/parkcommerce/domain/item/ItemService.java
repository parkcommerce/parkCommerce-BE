package com.commerce.parkcommerce.domain.item;

import com.commerce.parkcommerce.domain.item.entity.Item;
import com.commerce.parkcommerce.global.error.ErrorStatus;
import com.commerce.parkcommerce.global.error.exception.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ItemService {

    private final ItemRepository itemRepository;

    @Transactional
    public void createItem() {

    }

    public Item getItem(Long id) {
        return itemRepository
                .findById(id)
                .orElseThrow(() -> new NotFoundException(ErrorStatus.ITEM_NOT_FOUND.getMessage()));
    }
}
