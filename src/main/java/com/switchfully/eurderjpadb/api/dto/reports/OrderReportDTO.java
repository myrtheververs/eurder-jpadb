package com.switchfully.eurderjpadb.api.dto.reports;

import com.fasterxml.jackson.annotation.JsonCreator;

import java.util.List;

public class OrderReportDTO {

    private final Long orderId;
    private final List<ItemgroupReportDTO> itemgroupReportDTOList;

    @JsonCreator(mode = JsonCreator.Mode.PROPERTIES)
    public OrderReportDTO(Long orderId, List<ItemgroupReportDTO> itemgroupReportDTOList) {
        this.orderId = orderId;
        this.itemgroupReportDTOList = itemgroupReportDTOList;
    }

    public List<ItemgroupReportDTO> getItemgroupReportDTOList() {
        return itemgroupReportDTOList;
    }

    public Long getOrderId() {
        return orderId;
    }
}
