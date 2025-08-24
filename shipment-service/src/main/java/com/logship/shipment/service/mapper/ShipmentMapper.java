package com.logship.shipment.service.mapper;

import com.logship.shipment.service.controller.request.CreateShipmentRequest;
import com.logship.shipment.service.entity.Shipment;
import com.logship.shipment.service.entity.ShipmentOrders;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.util.List;
import java.util.UUID;

@Mapper(componentModel = "spring")
public interface ShipmentMapper {

    @Mapping(target = "shipmentOrders", source = "orders",qualifiedByName = "ordersToShipmentOrdersConversion")
    Shipment createRequestToEntity(CreateShipmentRequest createShipmentRequest);

    @Named("ordersToShipmentOrdersConversion")
    static List<ShipmentOrders> ordersToShipmentOrdersConversion(List<UUID> orderIds) {
        return orderIds.stream().map(orderId -> ShipmentOrders.builder().orderId(orderId).build()).toList();
    }
}
