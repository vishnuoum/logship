package com.logship.shipment.service.consumer;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.logship.shipment.service.dto.OrderCreatedEventDTO;
import com.logship.shipment.service.entity.PendingPickupOrder;
import com.logship.shipment.service.logging.LogUtil;
import com.logship.shipment.service.repository.PendingPickupOrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class OrderCreatedEventConsumer {

    private final ObjectMapper objectMapper;
    private final PendingPickupOrderRepository pendingPickupOrderRepository;

    @KafkaListener(topics = "orders", groupId = "shipment-service-group")
    public void orderCreatedEvent(String data) {
        try {
            OrderCreatedEventDTO orderCreatedEventDTO = objectMapper.readValue(data, OrderCreatedEventDTO.class);
            PendingPickupOrder pendingPickupOrder = PendingPickupOrder.builder().orderId(orderCreatedEventDTO.getOrderId()).build();
            pendingPickupOrderRepository.save(pendingPickupOrder);
        } catch (JsonProcessingException e) {
            LogUtil.printInfo(getClass(), "Error while converting json data to Order Created Event");
        } catch (Exception e) {
            LogUtil.printInfo(getClass(), "Error while saving order created event");
        }
    }
}
