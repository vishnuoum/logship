package com.logship.order.service.producers;

import com.logship.order.service.dto.OrderCreatedEventDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class OrderCreatedEventProducer {

    private final KafkaTemplate<String, Object> kafkaTemplate;

    public void sendOrderCreatedEvent(OrderCreatedEventDTO event) {
        kafkaTemplate.send("orders", event.getOrderId().toString(), event);
    }
}
