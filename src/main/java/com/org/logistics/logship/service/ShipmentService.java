package com.org.logistics.logship.service;

import com.org.logistics.logship.constants.Constants;
import com.org.logistics.logship.dto.ShipmentDetails;
import com.org.logistics.logship.dto.ShipmentMaster;
import com.org.logistics.logship.mappers.mapstruct.ShipmentStructMapper;
import com.org.logistics.logship.persistence.helper.OrderHelper;
import com.org.logistics.logship.persistence.helper.ShipmentHelper;
import com.org.logistics.logship.provider.request.AddShipmentOrdersRequest;
import com.org.logistics.logship.provider.request.CreateShipmentRequest;
import com.org.logistics.logship.provider.response.AddShipmentOrdersResponse;
import com.org.logistics.logship.provider.response.CreateShipmentResponse;
import com.org.logistics.logship.provider.response.LogShipResponse;
import com.org.logistics.logship.util.CommonUtil;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ShipmentService {

    private final OrderHelper orderHelper;
    private final ShipmentHelper shipmentHelper;
    private final ShipmentStructMapper shipmentStructMapper;

    ShipmentService(OrderHelper orderHelper, ShipmentHelper shipmentHelper, ShipmentStructMapper shipmentStructMapper) {
        this.orderHelper = orderHelper;
        this.shipmentHelper = shipmentHelper;
        this.shipmentStructMapper = shipmentStructMapper;
    }

    public ResponseEntity<CreateShipmentResponse> createShipment(CreateShipmentRequest shipmentRequest) {
        return LogShipResponse.ok(createNewShipment(shipmentRequest));
    }

    private CreateShipmentResponse createNewShipment(CreateShipmentRequest shipmentRequest) {
        ShipmentMaster shipmentMaster =  shipmentStructMapper.createRequestToShipmentMaster(shipmentRequest);
        shipmentHelper.createShipment(shipmentMaster);
        return shipmentStructMapper.shipmentMasterToCreateResponse(shipmentMaster);
    }

    public ResponseEntity<AddShipmentOrdersResponse> addShipmentOrders(AddShipmentOrdersRequest addShipmentOrdersRequest) {
        return LogShipResponse.ok(addOrdersToShipment(addShipmentOrdersRequest));
    }

    private AddShipmentOrdersResponse addOrdersToShipment(AddShipmentOrdersRequest addShipmentOrdersRequest) {
        shipmentHelper.addOrdersToShipment(
                CommonUtil.extractNumberFromId(addShipmentOrdersRequest.getShipmentId(), Constants.SHIPMENT_PREFIX),
                addShipmentOrdersRequest.getShipmentOrders().stream().map(orderId -> CommonUtil.extractNumberFromId(orderId, Constants.ORDER_PREFIX)).toList());
        return shipmentStructMapper.addShipOrderRequestToAddShipOrderResponse(addShipmentOrdersRequest);
    }

    @Transactional
    public void startShipment(String shipmentId) {
        Integer shipId = CommonUtil.extractNumberFromId(shipmentId, Constants.SHIPMENT_PREFIX);
        shipmentHelper.startShipment(shipId);
        ShipmentMaster shipmentMaster = shipmentHelper.getShipmentMaster(shipId);
        List<ShipmentDetails> shipmentDetails = Optional.ofNullable(shipmentHelper.getShipmentOrders(shipId)).orElse(new ArrayList<>());
        orderHelper.insertOrderStatus(Constants.OrderStatus.IT.name(), Optional.ofNullable(shipmentMaster).map(ShipmentMaster::getShipmentHandlerId).orElse(0), shipmentDetails.stream().map(ShipmentDetails::getOrderId).toList());
    }

    public void endShipment(String shipmentId) {
        Integer shipId = CommonUtil.extractNumberFromId(shipmentId, Constants.SHIPMENT_PREFIX);
        shipmentHelper.endShipment(shipId);
    }
}
