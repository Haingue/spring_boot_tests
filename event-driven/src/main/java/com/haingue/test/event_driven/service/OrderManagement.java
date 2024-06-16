package com.haingue.test.event_driven.service;

import com.haingue.test.event_driven.event.OrderCreationEvent;
import com.haingue.test.event_driven.model.Order;
import com.haingue.test.event_driven.repository.OrderRepository;
import jakarta.annotation.Resource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;

@Service
public class OrderManagement {
    private static final Logger LOGGER = LoggerFactory.getLogger(OrderManagement.class);

    private final ApplicationEventPublisher eventPublisher;

    @Resource
    private OrderRepository orderRepository;

    public OrderManagement(ApplicationEventPublisher eventPublisher) {
        this.eventPublisher = eventPublisher;
    }

    @Transactional
    public Order createNewOrder(Order newOrder) {
        LOGGER.debug("Start of OrderManagement complete function");
        // Order creation logic here
        // ...

        Order order = new Order.Builder()
                .id(newOrder.getId())
                .owner(newOrder.getOwner())
                .amount(newOrder.getAmount())
                .creationDatetime(Instant.now())
                .build();
        order = orderRepository.save(order);

        // Publish the OrderCreationEvent
        eventPublisher.publishEvent(new OrderCreationEvent(this, order));
        LOGGER.debug("End of OrderManagement complete function");
        return order;
    }

}
