package com.org.logistics.logship.service;


import com.org.logistics.logship.constants.Constants;
import com.org.logistics.logship.dto.HandlerDetails;
import com.org.logistics.logship.dto.OrderDetails;
import com.org.logistics.logship.mappers.mapstruct.OrderStatusStructMapper;
import com.org.logistics.logship.mappers.mapstruct.OrderStructMapper;
import com.org.logistics.logship.persistence.helper.HandlerHelper;
import com.org.logistics.logship.persistence.helper.OrderHelper;
import com.org.logistics.logship.provider.request.PlaceOrderRequest;
import com.org.logistics.logship.provider.request.UpdateOrderStatusRequest;
import com.org.logistics.logship.provider.response.LogShipResponse;
import com.org.logistics.logship.provider.response.PlaceOrderResponse;
import com.org.logistics.logship.util.CommonUtil;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class OrderService {

    private final OrderHelper orderHelper;
    private final HandlerHelper handlerHelper;
    private final OrderStructMapper orderStructMapper;
    private final OrderStatusStructMapper orderStatusStructMapper;


    OrderService(OrderHelper orderHelper, HandlerHelper handlerHelper, OrderStructMapper orderStructMapper, OrderStatusStructMapper orderStatusStructMapper) {
        this.orderHelper = orderHelper;
        this.handlerHelper = handlerHelper;
        this.orderStructMapper = orderStructMapper;
        this.orderStatusStructMapper = orderStatusStructMapper;
    }

    @Transactional
    public ResponseEntity<PlaceOrderResponse> placeOrder(PlaceOrderRequest orderRequest) {
        return LogShipResponse.ok(placeNewOrder(orderRequest));
    }

    private PlaceOrderResponse placeNewOrder(PlaceOrderRequest orderRequest) {
        OrderDetails orderDetails = orderStructMapper.requestToOrderDetails(orderRequest);
        orderHelper.insertOrderDetails(orderDetails);
        orderHelper.insertOrderStatus(orderStatusStructMapper.assembleStatus(orderRequest, orderDetails, Constants.OrderStatus.OC.name()));
        orderHelper.insertOrderQualityChecks(orderDetails.getOrderId(), extractQualityCheckIds(orderRequest.getQualityCheckIds()));
        return orderStructMapper.orderDetailsToPlaceOrderResponse(orderDetails);
    }

    private List<Integer> extractQualityCheckIds(List<String> qualityCheckIds) {
        return qualityCheckIds.stream().map(qc -> CommonUtil.extractNumberFromId(qc, Constants.QUALITY_CHECK_PREFIX)).toList();
    }

    public void updateOrderStatus(UpdateOrderStatusRequest updateOrderStatusRequest) {
        OrderDetails orderDetails = orderHelper.getOrderDetails(CommonUtil.extractNumberFromId(updateOrderStatusRequest.getOrderId(), Constants.ORDER_PREFIX));
        HandlerDetails handlerDetails = handlerHelper.getHandlerDetails(CommonUtil.extractNumberFromId(updateOrderStatusRequest.getOrderId(), Constants.HANDLER_PREFIX));
        boolean isDestinationWarehouse = Optional.ofNullable(orderDetails).map(OrderDetails::getDestinationWarehouseId).orElse(0).equals(
                Optional.ofNullable(handlerDetails).map(HandlerDetails::getWarehouseId).orElse(-1)
        );
        orderHelper.insertOrderStatus(orderStatusStructMapper.assembleStatus(updateOrderStatusRequest, isDestinationWarehouse?Constants.OrderStatus.OD.name():Constants.OrderStatus.IMW.name()));
    }

}
