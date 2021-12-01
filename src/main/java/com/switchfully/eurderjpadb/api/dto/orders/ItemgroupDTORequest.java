package com.switchfully.eurderjpadb.api.dto.orders;

import lombok.Getter;

@Getter
public class ItemgroupDTORequest {
    private final Long ItemId;
    private final int amount;

    public ItemgroupDTORequest(Long itemId, int amount) {
        ItemId = itemId;
        this.amount = amount;
    }


}
