package com.org.logistics.logship.mappers.mapstruct;

import com.org.logistics.logship.dao.OrderDetails;
import com.org.logistics.logship.provider.request.PlaceOrderRequest;
import com.org.logistics.logship.provider.response.PlaceOrderResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface OrderStructMapper {

    @Mapping(target = "admissionWarehouseId",
            expression = "java(com.org.logistics.logship.util.CommonUtil.extractNumberFromId(placeOrderRequest.getAdmissionWarehouseId(), com.org.logistics.logship.constants.Constants.WAREHOUSE_PREFIX))")
    @Mapping(target = "destinationWarehouseId",
            expression = "java(com.org.logistics.logship.util.CommonUtil.extractNumberFromId(placeOrderRequest.getDestinationWarehouseId(), com.org.logistics.logship.constants.Constants.WAREHOUSE_PREFIX))")
    @Mapping(target = "senderId",
            expression = "java(com.org.logistics.logship.util.CommonUtil.extractNumberFromId(placeOrderRequest.getUserId(), com.org.logistics.logship.constants.Constants.USER_PREFIX))")
    OrderDetails requestToOrderDetails(PlaceOrderRequest placeOrderRequest);

    @Mapping(target = "orderInfo",
            expression = "java(com.org.logistics.logship.util.CommonUtil.appendPrefixToId(orderDetails.getOrderId(), com.org.logistics.logship.constants.Constants.ORDER_PREFIX))")
    PlaceOrderResponse orderDetailsToPlaceOrderResponse(OrderDetails orderDetails);
}
