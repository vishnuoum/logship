package com.logship.order.service.mapper;

import com.logship.order.service.controller.request.CreateOrderRequest;
import com.logship.order.service.dto.OrderDTO;
import com.logship.order.service.entity.Order;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface OrderMapper {

    Order mapOrderFromRequest(CreateOrderRequest createOrderRequest);

    List<OrderDTO> mapToDTO(List<Order> orders);

    OrderDTO mapToDTO(Order order);
}
