package com.haingue.test.event_driven.repository;

import com.haingue.test.event_driven.model.Order;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface OrderRepository extends CrudRepository<Order, UUID> {
    List<Order> findByOwner(String owner);
}
