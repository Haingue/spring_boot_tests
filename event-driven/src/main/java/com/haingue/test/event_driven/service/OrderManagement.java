package com.haingue.test.event_driven.service;

import com.haingue.test.event_driven.model.Order;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class OrderManagement {
    private final ApplicationEventPublisher events;

    public OrderManagement(ApplicationEventPublisher events) {
        this.events = events;
    }

    @Transactional
    public void complete(Order order) {

        // State transition on the order aggregate go here

        // Invoke related functionality
//        inventory.updateStockFor(order);
    }

}
