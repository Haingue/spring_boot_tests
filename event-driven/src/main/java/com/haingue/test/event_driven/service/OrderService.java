package com.haingue.test.event_driven.service;

import com.haingue.test.event_driven.model.Order;
import com.haingue.test.event_driven.repository.OrderRepository;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;
import java.util.stream.StreamSupport;

@Service
public class OrderService {
    @Resource
    private OrderRepository orderRepository;

    public Order save (Order newOrder) {
        newOrder.setCreationDatetime(Instant.now());
        return orderRepository.save(newOrder);
    }

    public List<Order> findAll () {
        return StreamSupport.stream(orderRepository.findAll().spliterator(), false).toList();
    }

    public List<Order> findAllByOwner (String owner) {
        return orderRepository.findByOwner(owner);
    }
}
