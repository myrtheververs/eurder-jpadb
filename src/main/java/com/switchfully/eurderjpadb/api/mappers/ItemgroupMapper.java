package com.switchfully.eurderjpadb.api.mappers;


import com.switchfully.eurderjpadb.domain.entities.Item;
import com.switchfully.eurderjpadb.domain.entities.Order;
import com.switchfully.eurderjpadb.domain.entities.Itemgroup;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class ItemgroupMapper {

    public Itemgroup toEntity(Item item, int amount, LocalDate shippingDate, Order order) {
        return Itemgroup.builder()
                .order(order)
                .item(item)
                .name(item.getName())
                .description(item.getDescription())
                .amount(amount)
                .salePrice(item.getPrice())
                .shippingDate(shippingDate)
                .build();
    }
}
