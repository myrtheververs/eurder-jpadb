package com.switchfully.eurderjpadb.api.dto.orders;

import com.fasterxml.jackson.annotation.JsonCreator;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
public class OrderDTORequest {

    private final List<ItemgroupDTORequest> itemgroupDTORequests = new ArrayList<>();

    @JsonCreator(mode = JsonCreator.Mode.PROPERTIES)
    public OrderDTORequest(List<ItemgroupDTORequest> itemgroupDTORequests) {
        this.itemgroupDTORequests.addAll(itemgroupDTORequests);
    }



}
