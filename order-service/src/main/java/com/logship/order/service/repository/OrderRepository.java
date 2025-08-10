package com.logship.order.service.repository;

import com.logship.order.service.entity.Order;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.UUID;

public interface OrderRepository extends CrudRepository<Order, UUID> {

    List<Order> findAllByCustomerId(UUID customerId);
}
