package com.logship.tracker.service.mapper;


import com.logship.tracker.service.dto.OrderCreatedEventDTO;
import com.logship.tracker.service.dto.OrderUpdateEventDTO;
import com.logship.tracker.service.entity.OrderStatus;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface OrderStatusMapper {

    @Mapping(target = "status", constant = "ORDER_PLACED")
    OrderStatus mapToEntityFromDTO(OrderCreatedEventDTO eventDTO);

    OrderStatus mapToEntityFromDTO(OrderUpdateEventDTO eventDTO);
}
