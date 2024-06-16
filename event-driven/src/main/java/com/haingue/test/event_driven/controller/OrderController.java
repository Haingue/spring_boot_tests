package com.haingue.test.event_driven.controller;

import com.haingue.test.event_driven.model.Order;
import com.haingue.test.event_driven.service.OrderManagement;
import com.haingue.test.event_driven.service.OrderService;
import jakarta.annotation.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@RequestMapping("/service/order")
public class OrderController {

    @Resource
    private OrderService orderService;
    @Resource
    private OrderManagement orderManagement;

    @PostMapping
    public Order saveOrder (@RequestBody Order newOrder) {
        return orderManagement.createNewOrder(newOrder);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public Collection<Order> getAllOrderByOwner (@RequestParam(required = false) String owner) {
        if (owner != null) {
            return orderService.findAllByOwner(owner);
        }
        return orderService.findAll();
    }

}
