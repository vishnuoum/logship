package com.logship.tracker.service.repository;

import com.logship.tracker.service.entity.OrderStatus;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface OrderStatusRepository extends CrudRepository<OrderStatus, Long> {

    @Query("SELECT o FROM OrderStatus o WHERE o.orderId = :orderId ORDER BY timestamp DESC LIMIT 1")
    Optional<OrderStatus> getLatestOrderStatus(@Param("orderId") UUID orderId);
}
