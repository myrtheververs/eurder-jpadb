package com.switchfully.eurderjpadb.api;


import com.switchfully.eurderjpadb.api.dto.orders.OrderDTORequest;
import com.switchfully.eurderjpadb.api.dto.orders.OrderDTOResponse;
import com.switchfully.eurderjpadb.sevices.OrderService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping(path = "/orders")
public class OrderController {

    private final OrderService orderService;
    private final Logger logger = LoggerFactory.getLogger(OrderController.class);

    @Autowired
    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    //POST
    @PostMapping(consumes = "application/json", produces = "application/json")
    @ResponseStatus(HttpStatus.CREATED)
    public OrderDTOResponse createOrder(@RequestHeader(value = "customerId") Long customerId,
                                        @RequestBody OrderDTORequest orderDTORequest) {
        logger.info("User with id " + customerId + " is making an order");
        return orderService.save(customerId, orderDTORequest);
    }
}
