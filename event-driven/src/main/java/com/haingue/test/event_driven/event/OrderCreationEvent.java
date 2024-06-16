package com.haingue.test.event_driven.event;

import com.haingue.test.event_driven.model.Order;
import org.springframework.context.ApplicationEvent;

public class OrderCreationEvent extends ApplicationEvent {
    private Order order;
    public OrderCreationEvent(Object source, Order order) {
        super(source);
        this.order = order;
    }

    public Order getOrder() {
        return order;
    }
}
