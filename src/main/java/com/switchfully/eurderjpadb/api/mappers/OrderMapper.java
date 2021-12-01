package com.switchfully.eurderjpadb.api.mappers;


import com.switchfully.eurderjpadb.api.dto.orders.OrderDTOResponse;
import com.switchfully.eurderjpadb.api.dto.orders.ItemgroupDTOResponse;
import com.switchfully.eurderjpadb.domain.entities.Order;
import com.switchfully.eurderjpadb.domain.entities.User;
import org.springframework.stereotype.Component;


@Component
public class OrderMapper {

    public OrderDTOResponse toDTO(Order order) {

        OrderDTOResponse orderDTOResponse = new OrderDTOResponse(order.getId(), order.getCustomer().getId(), order.getTotalPriceOfOrder());

        order.getItemgroups().stream()
                .map(itemgroup -> new ItemgroupDTOResponse(itemgroup.getItem().getId(), itemgroup.getSalePrice(), itemgroup.getAmount(), itemgroup.getOrderlineTotal(), itemgroup.getShippingDate()))
                .forEach(orderDTOResponse::addOrderlineDTO);
        return orderDTOResponse;
    }

    public Order toEntity(User customer){
        return Order.builder()
                .customer(customer)
                .build();
    }

}
