package com.logship.tracker.service.consumer;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.logship.tracker.service.dto.OrderCreatedEventDTO;
import com.logship.tracker.service.dto.OrderUpdateEventDTO;
import com.logship.tracker.service.entity.OrderStatus;
import com.logship.tracker.service.logging.LogUtil;
import com.logship.tracker.service.mapper.OrderStatusMapper;
import com.logship.tracker.service.repository.OrderStatusRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class OrderEventConsumer {

    private final ObjectMapper objectMapper;
    private final OrderStatusMapper orderStatusMapper;
    private final OrderStatusRepository orderStatusRepository;

    @KafkaListener(topics = "orders", groupId = "tracker-service-group")
    public void orderCreatedEvent(String data) {
        try {
            OrderCreatedEventDTO orderCreatedEventDTO = objectMapper.readValue(data, OrderCreatedEventDTO.class);
            OrderStatus orderStatus = orderStatusMapper.mapToEntityFromDTO(orderCreatedEventDTO);
            orderStatusRepository.save(orderStatus);
        } catch (JsonProcessingException e) {
            LogUtil.printInfo(getClass(), "Error while converting json data to Order Created Event");
        } catch (Exception e) {
            LogUtil.printInfo(getClass(), "Error while saving order created event");
        }
    }

    @KafkaListener(topics = "order-status", groupId = "tracker-service-group")
    public void orderUpdateEvent(String data) {
        try {
            OrderUpdateEventDTO orderUpdateEventDTO = objectMapper.readValue(data, OrderUpdateEventDTO.class);
            OrderStatus orderStatus = orderStatusMapper.mapToEntityFromDTO(orderUpdateEventDTO);
            orderStatusRepository.save(orderStatus);
        } catch (JsonProcessingException e) {
            LogUtil.printInfo(getClass(), "Error while converting json data to Order Updated Event");
        } catch (Exception e) {
            LogUtil.printInfo(getClass(), "Error while saving order update event");
        }
    }
}
