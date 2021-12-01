package com.switchfully.eurderjpadb.api.mappers;


import com.switchfully.eurderjpadb.api.dto.reports.OrderReportDTO;
import com.switchfully.eurderjpadb.api.dto.reports.ItemgroupReportDTO;
import com.switchfully.eurderjpadb.api.dto.reports.OrdersReportForCustomerDTO;
import com.switchfully.eurderjpadb.domain.entities.Itemgroup;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.List;

@Component
public class ReportMapper {

    public OrderReportDTO toOrderReportDTO(Long orderId, List<ItemgroupReportDTO> orderlines) {
        return new OrderReportDTO(orderId, orderlines);
    }

    public ItemgroupReportDTO toItemgroupReportDTO(Itemgroup itemGroup) {
        return new ItemgroupReportDTO(itemGroup.getName(), itemGroup.getAmount(), itemGroup.getOrderlineTotal());
    }

    public OrdersReportForCustomerDTO toOrdersReportForCustomerDTO(List<OrderReportDTO> listOfAllOrdersFromCustomer, BigDecimal totalOfAllOrders) {
        return new OrdersReportForCustomerDTO(listOfAllOrdersFromCustomer, totalOfAllOrders);
    }
}
