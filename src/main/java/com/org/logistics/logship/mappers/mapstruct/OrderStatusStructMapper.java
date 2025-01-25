package com.org.logistics.logship.mappers.mapstruct;

import com.org.logistics.logship.constants.Constants;
import com.org.logistics.logship.dao.OrderDetails;
import com.org.logistics.logship.dao.OrderStatus;
import com.org.logistics.logship.provider.request.PlaceOrderRequest;
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
}
