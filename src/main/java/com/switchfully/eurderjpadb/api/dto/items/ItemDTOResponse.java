package com.switchfully.eurderjpadb.api.dto.items;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.math.BigDecimal;

@Getter
@AllArgsConstructor
@Builder
public class ItemDTOResponse {

    private final Long id;
    private final String name;
    private final String description;
    private final BigDecimal price;
    private final int amountInStock;



}
