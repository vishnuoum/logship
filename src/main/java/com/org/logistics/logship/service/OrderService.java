package com.org.logistics.logship.service;


import com.org.logistics.logship.constants.Constants;
import com.org.logistics.logship.dao.OrderDetails;
import com.org.logistics.logship.mappers.mapstruct.OrderStatusStructMapper;
import com.org.logistics.logship.mappers.mapstruct.OrderStructMapper;
import com.org.logistics.logship.persistence.helper.OrderHelper;
import com.org.logistics.logship.provider.request.PlaceOrderRequest;
import com.org.logistics.logship.provider.response.LogShipResponse;
import com.org.logistics.logship.provider.response.PlaceOrderResponse;
import com.org.logistics.logship.util.CommonUtil;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class OrderService {

    private final OrderHelper orderHelper;
    private final OrderStructMapper orderStructMapper;
    private final OrderStatusStructMapper orderStatusStructMapper;


    OrderService(OrderHelper orderHelper, OrderStructMapper orderStructMapper, OrderStatusStructMapper orderStatusStructMapper) {
        this.orderHelper = orderHelper;
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

}
