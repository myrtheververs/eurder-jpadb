package com.switchfully.eurderjpadb.api.dto.orders;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@AllArgsConstructor
public class ItemgroupDTOResponse {

    private final Long itemId;
    private final BigDecimal salesPrice;
    private final int amount;
    private final BigDecimal subTotal;
    private final LocalDate shippingDate;


}
