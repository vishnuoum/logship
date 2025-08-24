package com.logship.shipment.service.consumer;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.logship.shipment.service.dto.OrderCreatedEventDTO;
import com.logship.shipment.service.entity.PendingPickup;
import com.logship.shipment.service.logging.LogUtil;
import com.logship.shipment.service.repository.PendingPickupRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class OrderCreatedEventConsumer {

    private final ObjectMapper objectMapper;
    private final PendingPickupRepository pendingPickupRepository;

    @KafkaListener(topics = "orders", groupId = "shipment-service-group")
    public void orderCreatedEvent(String data) {
        try {
            OrderCreatedEventDTO orderCreatedEventDTO = objectMapper.readValue(data, OrderCreatedEventDTO.class);
            PendingPickup pendingPickup = PendingPickup.builder().orderId(orderCreatedEventDTO.getOrderId()).build();
            pendingPickupRepository.save(pendingPickup);
        } catch (JsonProcessingException e) {
            LogUtil.printInfo(getClass(), "Error while converting json data to Order Created Event");
        } catch (Exception e) {
            LogUtil.printInfo(getClass(), "Error while saving order created event");
        }
    }
}
