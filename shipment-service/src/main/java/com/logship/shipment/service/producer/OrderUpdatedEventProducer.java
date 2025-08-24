package com.logship.shipment.service.producer;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.logship.shipment.service.dto.OrderUpdatedEventDTO;
import com.logship.shipment.service.logging.LogUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class OrderUpdatedEventProducer {

    private final ObjectMapper objectMapper;
    private final KafkaTemplate<String, String> kafkaTemplate;

    public void sendOrderUpdateEvent(OrderUpdatedEventDTO event) {
        try {
            kafkaTemplate.send("order-status", event.getOrderId().toString(), objectMapper.writeValueAsString(event));
        } catch (JsonProcessingException e) {
            LogUtil.printInfo(getClass(), "Error while converting Order Updated Event to json");
        }
    }
}
