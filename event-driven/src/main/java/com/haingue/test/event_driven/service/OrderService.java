package com.haingue.test.event_driven.service;

import com.haingue.test.event_driven.model.Order;
import com.haingue.test.event_driven.repository.OrderRepository;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.stream.StreamSupport;

@Service
public class OrderService {
    @Resource
    private OrderRepository orderRepository;

    public Collection<Order> findAll () {
        return StreamSupport.stream(orderRepository.findAll().spliterator(), false).toList();
    }

    public Collection<Order> findAllByOwner (String owner) {
        return orderRepository.findByOwner(owner);
    }
}
