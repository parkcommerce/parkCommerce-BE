package com.commerce.parkcommerce.domain.item.entity.cosmetics;

import com.commerce.parkcommerce.domain.item.entity.Item;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;


@Entity
@DiscriminatorValue("COSMETICS")
public class Cosmetics extends Item {

    private String company;
}
