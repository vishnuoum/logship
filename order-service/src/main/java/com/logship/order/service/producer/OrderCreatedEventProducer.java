package com.logship.order.service.producer;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.logship.order.service.dto.OrderCreatedEventDTO;
import com.logship.order.service.logging.LogUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class OrderCreatedEventProducer {

    private final ObjectMapper objectMapper;
    private final KafkaTemplate<String, String> kafkaTemplate;

    public void sendOrderCreatedEvent(OrderCreatedEventDTO event) {
        try {
            kafkaTemplate.send("orders", event.getOrderId().toString(), objectMapper.writeValueAsString(event));
        } catch (JsonProcessingException e) {
            LogUtil.printInfo(getClass(), "Error while converting Order Created Event to json");
        }
    }
}
