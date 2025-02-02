package com.org.logistics.logship.service;


import com.org.logistics.logship.constants.Constants;
import com.org.logistics.logship.dto.HandlerDetails;
import com.org.logistics.logship.dto.OrderDetails;
import com.org.logistics.logship.dto.QualityCheckObject;
import com.org.logistics.logship.mappers.mapstruct.OrderStatusStructMapper;
import com.org.logistics.logship.mappers.mapstruct.OrderStructMapper;
import com.org.logistics.logship.mappers.mapstruct.QualityCheckStructMapper;
import com.org.logistics.logship.persistence.helper.HandlerHelper;
import com.org.logistics.logship.persistence.helper.OrderHelper;
import com.org.logistics.logship.persistence.helper.QualityCheckHelper;
import com.org.logistics.logship.persistence.helper.ShipmentHelper;
import com.org.logistics.logship.provider.request.PlaceOrderRequest;
import com.org.logistics.logship.provider.request.UpdateOrderStatusRequest;
import com.org.logistics.logship.provider.response.GetAllQCResponse;
import com.org.logistics.logship.provider.response.LogShipResponse;
import com.org.logistics.logship.provider.response.PlaceOrderResponse;
import com.org.logistics.logship.provider.response.bo.ModifiedQualityCheckObject;
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
    private final ShipmentHelper shipmentHelper;
    private final OrderStructMapper orderStructMapper;
    private final QualityCheckHelper qualityCheckHelper;
    private final OrderStatusStructMapper orderStatusStructMapper;


    OrderService(OrderHelper orderHelper, HandlerHelper handlerHelper, ShipmentHelper shipmentHelper, OrderStructMapper orderStructMapper, QualityCheckHelper qualityCheckHelper, OrderStatusStructMapper orderStatusStructMapper) {
        this.orderHelper = orderHelper;
        this.handlerHelper = handlerHelper;
        this.shipmentHelper = shipmentHelper;
        this.orderStructMapper = orderStructMapper;
        this.qualityCheckHelper = qualityCheckHelper;
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
        qualityCheckHelper.insertOrderQualityChecks(orderDetails.getOrderId(), extractQualityCheckIds(orderRequest.getQualityCheckIds()));
        return orderStructMapper.orderDetailsToPlaceOrderResponse(orderDetails);
    }

    private List<Integer> extractQualityCheckIds(List<String> qualityCheckIds) {
        return qualityCheckIds.stream().map(qc -> CommonUtil.extractNumberFromId(qc, Constants.QUALITY_CHECK_PREFIX)).toList();
    }

    @Transactional
    public void updateOrderStatus(UpdateOrderStatusRequest updateOrderStatusRequest) {
        Integer orderId = CommonUtil.extractNumberFromId(updateOrderStatusRequest.getOrderId(), Constants.ORDER_PREFIX);
        OrderDetails orderDetails = orderHelper.getOrderDetails(orderId);
        HandlerDetails handlerDetails = handlerHelper.getHandlerDetails(CommonUtil.extractNumberFromId(updateOrderStatusRequest.getHandlerId(), Constants.HANDLER_PREFIX));
        boolean isDestinationWarehouse = Optional.ofNullable(orderDetails).map(OrderDetails::getDestinationWarehouseId).orElse(0).equals(
                Optional.ofNullable(handlerDetails).map(HandlerDetails::getWarehouseId).orElse(-1)
        );
        orderHelper.insertOrderStatus(orderStatusStructMapper.assembleStatus(updateOrderStatusRequest, isDestinationWarehouse?Constants.OrderStatus.OD.name():Constants.OrderStatus.IMW.name()));
        shipmentHelper.updateTakeInDate(CommonUtil.extractNumberFromId(updateOrderStatusRequest.getShipmentId(), Constants.SHIPMENT_PREFIX), orderId);
    }


}
