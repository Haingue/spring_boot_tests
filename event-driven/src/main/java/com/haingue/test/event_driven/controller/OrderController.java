package com.haingue.test.event_driven.controller;

import com.haingue.test.event_driven.model.Order;
import com.haingue.test.event_driven.service.OrderService;
import jakarta.annotation.Resource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/service/order")
public class OrderController {

    private static final Logger LOGGER = LoggerFactory.getLogger(OrderService.class);

    @Resource
    private OrderService orderService;

    @PostMapping
    public Order saveOrder (@RequestBody Order newOrder) {
        LOGGER.info("Create new order: {}", newOrder);
        return orderService.save(newOrder);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<Order> getAllOrderByOwner (@RequestParam(required = false) String owner) {
        if (owner != null) {
            return orderService.findAllByOwner(owner);
        }
        return orderService.findAll();
    }

}
