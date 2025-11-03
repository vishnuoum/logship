package com.logship.tracker.service.service;

import com.logship.tracker.service.entity.OrderStatus;
import com.logship.tracker.service.entity.Status;
import com.logship.tracker.service.exception.ExceptionManager;
import com.logship.tracker.service.repository.OrderStatusRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@AllArgsConstructor
public class TrackerService {

    private OrderStatusRepository orderStatusRepository;

    public String getOrderStatus(UUID orderId) {
        return orderStatusRepository.getLatestOrderStatus(orderId)
                .map(OrderStatus::getStatus).map(Status::name)
                .orElseThrow(() -> ExceptionManager.throwException(ExceptionManager.ERRORCODE.ORDER_NOT_EXISTS_ERROR));
    }
}
