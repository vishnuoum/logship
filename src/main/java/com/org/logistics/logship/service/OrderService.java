package com.org.logistics.logship.service;

import com.org.logistics.logship.helper.CommonHelper;
import com.org.logistics.logship.mappers.OrderMapper;
import com.org.logistics.logship.provider.dto.response.PlaceOrderDtoResponse;
import com.org.logistics.logship.provider.request.PlaceOrderRequest;
import com.org.logistics.logship.provider.response.LogShipResponse;
import com.org.logistics.logship.provider.response.PlaceOrderResponse;
import com.org.logistics.logship.util.CommonUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class OrderService {


    private final OrderMapper orderMapper;
    private final Environment environment;
    private final CommonHelper commonHelper;

    @Autowired
    OrderService(CommonHelper commonHelper, OrderMapper orderMapper, Environment environment) {
        this.orderMapper = orderMapper;
        this.environment = environment;
        this.commonHelper = commonHelper;
    }

    public ResponseEntity<PlaceOrderResponse> placeOrder(PlaceOrderRequest orderRequest) {
        return LogShipResponse.ok(placeNewOrder(orderRequest));
    }

    private PlaceOrderResponse placeNewOrder(PlaceOrderRequest orderRequest) {
        String daoResponse = commonHelper.connectWithDataService(orderMapper.mapOrderRequestToOrderDtoRequest(orderRequest), environment.getProperty("logship.data.endpoint.placeorder"));
        return orderMapper.mapOrderDtoResponseToOrderResponse((PlaceOrderDtoResponse) CommonUtil.convertJsonToObject(daoResponse, PlaceOrderDtoResponse.class));
    }

}
