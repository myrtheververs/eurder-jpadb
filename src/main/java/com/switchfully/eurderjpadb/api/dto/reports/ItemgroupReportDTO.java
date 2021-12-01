package com.switchfully.eurderjpadb.api.dto.reports;

import lombok.Getter;

import java.math.BigDecimal;

@Getter
public class ItemgroupReportDTO {

    private final String itemName;
    private final int amountOrdered;
    private BigDecimal itemgroupTotal;

    public ItemgroupReportDTO(String itemName, int amountOrdered, BigDecimal itemgroupTotal) {
        this.itemName = itemName;
        this.amountOrdered = amountOrdered;
        this.itemgroupTotal = itemgroupTotal;
    }

}
