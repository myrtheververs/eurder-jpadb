package com.switchfully.eurderjpadb.sevices;


import com.switchfully.eurderjpadb.api.dto.orders.OrderDTORequest;
import com.switchfully.eurderjpadb.api.dto.orders.ItemgroupDTORequest;
import com.switchfully.eurderjpadb.api.dto.orders.OrderDTOResponse;
import com.switchfully.eurderjpadb.api.mappers.OrderMapper;
import com.switchfully.eurderjpadb.api.mappers.ItemgroupMapper;
import com.switchfully.eurderjpadb.domain.entities.Item;
import com.switchfully.eurderjpadb.domain.entities.Order;
import com.switchfully.eurderjpadb.domain.entities.Itemgroup;
import com.switchfully.eurderjpadb.domain.entities.User;
import com.switchfully.eurderjpadb.repositories.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Service
@Transactional
public class OrderService {

    private static final int SHORTTERM_SHIPPING_DAYS = 1;
    private static final int LONGTERM_SHIPPING_DAYS = 7;

    private final OrderMapper orderMapper;
    private final ItemgroupMapper itemgroupMapper;
    private final UserService userService;
    private final ItemService itemService;
    private final OrderRepository orderRepository;

    @Autowired
    public OrderService(OrderMapper orderMapper, ItemgroupMapper itemgroupMapper, UserService userService, ItemService itemService, OrderRepository orderRepository) {
        this.orderMapper = orderMapper;
        this.itemgroupMapper = itemgroupMapper;
        this.userService = userService;
        this.itemService = itemService;
        this.orderRepository = orderRepository;
    }

    public OrderDTOResponse save(Long customerId, OrderDTORequest orderDTORequest) {
        userService.assertValidCustomerId(customerId);

        Order order = processOrder(orderDTORequest, customerId);

        return orderMapper.toDTO(orderRepository.save(order));
    }

    private Order processOrder(OrderDTORequest orderDTORequest, Long userId) {

        User user = userService.findById(userId);
        orderDTORequest.getItemgroupDTORequests().forEach(this::assertOrderlineIdAndOrderedAmount);

        Order order = orderMapper.toEntity(user);

        Set<Itemgroup> itemgroups = new HashSet<>();

        orderDTORequest.getItemgroupDTORequests().forEach(
                orderline -> itemgroups.add(processOrderline(orderline, order)));

        order.setItemgroups(itemgroups);

        return order;
    }

    private Itemgroup processOrderline(ItemgroupDTORequest orderline, Order order) {

        Item item = itemService.getById(orderline.getItemId());

        item.takeItemsFromStock(orderline.getAmount());

        LocalDate shippingDate = calculateShippingDate(orderline.getAmount(), item.getAmountInStock());

        return itemgroupMapper.toEntity(item, orderline.getAmount(), shippingDate, order);
    }

    private LocalDate calculateShippingDate(int amountOrdered, int amountInStock) {
        if (amountInStock >= amountOrdered) {
            return LocalDate.now().plusDays(SHORTTERM_SHIPPING_DAYS);
        } else {
            return LocalDate.now().plusDays(LONGTERM_SHIPPING_DAYS);
        }
    }

    private void assertOrderlineIdAndOrderedAmount(ItemgroupDTORequest orderline) {
        itemService.assertItemId(orderline.getItemId());
        assertOrderlineAmountGreaterThanZero(orderline.getAmount());
    }

    private void assertOrderlineAmountGreaterThanZero(int amount) {
        if (amount <= 0) {
            throw new IllegalArgumentException("You can't order 0 or less of an item.");
        }
    }

}
