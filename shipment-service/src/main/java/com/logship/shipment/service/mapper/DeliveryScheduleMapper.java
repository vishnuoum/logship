package com.logship.shipment.service.mapper;

import com.logship.shipment.service.controller.request.model.OrderDetails;
import com.logship.shipment.service.entity.DeliverySchedule;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface DeliveryScheduleMapper {
    List<DeliverySchedule> orderDetailsToDeliverySchedule(List<OrderDetails> orderDetails);
}
