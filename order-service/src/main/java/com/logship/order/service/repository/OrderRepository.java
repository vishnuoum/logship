package com.logship.order.service.repository;

import com.logship.order.service.entity.Order;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.UUID;

public interface OrderRepository extends CrudRepository<Order, UUID> {

    List<Order> findAllByCustomerId(UUID customerId);

    @Modifying
    @Query("Update Order o set o.weight = :weight where o.id = :orderId")
    void updateOrderWeight(@Param("orderId") UUID orderId, @Param("weight") Double weight);
}
