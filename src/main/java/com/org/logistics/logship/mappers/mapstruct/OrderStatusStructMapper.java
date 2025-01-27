package com.org.logistics.logship.mappers.mapstruct;

import com.org.logistics.logship.dto.OrderDetails;
import com.org.logistics.logship.dto.OrderStatus;
import com.org.logistics.logship.provider.request.PlaceOrderRequest;
import com.org.logistics.logship.provider.request.UpdateOrderStatusRequest;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface OrderStatusStructMapper {

    @Mapping(target = "status", source = "status")
    @Mapping(target = "orderId", source = "orderDetails.orderId")
    @Mapping(target = "handlerId", expression = "java(com.org.logistics.logship.util.CommonUtil.extractNumberFromId(placeOrderRequest.getHandlerId(), com.org.logistics.logship.constants.Constants.HANDLER_PREFIX))")
    OrderStatus assembleStatus(PlaceOrderRequest placeOrderRequest, OrderDetails orderDetails, String status);

    @Mapping(target = "status", source = "status")
    @Mapping(target = "orderId", source = "updateOrderStatusRequest.orderId")
    @Mapping(target = "handlerId", source = "updateOrderStatusRequest.handlerId")
    OrderStatus assembleStatus(UpdateOrderStatusRequest updateOrderStatusRequest, String status);
}
