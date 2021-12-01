package com.switchfully.eurderjpadb.api.dto.orders;

import lombok.Getter;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

@Getter
public class OrderDTOResponse {

    private final Long id;
    private final Long customerId;
    private final Set<ItemgroupDTOResponse> itemgroupDTOResponseSet;
    private final BigDecimal totalOrderPrice;

    public OrderDTOResponse(Long id, Long customerId, BigDecimal totalOrderPrice) {
        itemgroupDTOResponseSet = new HashSet<>();
        this.id = id;
        this.customerId = customerId;
        this.totalOrderPrice = totalOrderPrice;
    }


    public void addOrderlineDTO(ItemgroupDTOResponse itemgroupDTOResponse){
        itemgroupDTOResponseSet.add(itemgroupDTOResponse);
    }

}
