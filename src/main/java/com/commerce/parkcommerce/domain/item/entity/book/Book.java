package com.commerce.parkcommerce.domain.item.entity.book;

import com.commerce.parkcommerce.domain.item.entity.Item;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@Entity
@DiscriminatorValue("BOOK")
public class Book extends Item {

    private String author;
    private String isbn;
}
