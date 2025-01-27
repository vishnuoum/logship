package com.org.logistics.logship.mappers.mapstruct;

import com.org.logistics.logship.dto.ShipmentMaster;
import com.org.logistics.logship.provider.request.AddShipmentOrdersRequest;
import com.org.logistics.logship.provider.request.CreateShipmentRequest;
import com.org.logistics.logship.provider.response.AddShipmentOrdersResponse;
import com.org.logistics.logship.provider.response.CreateShipmentResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface ShipmentStructMapper {

    @Mapping(target = "shipmentHandlerId",
            expression = "java(com.org.logistics.logship.util.CommonUtil.extractNumberFromId(shipmentRequest.getHandlerId(), com.org.logistics.logship.constants.Constants.HANDLER_PREFIX))")
    @Mapping(target = "fromWarehouseId",
            expression = "java(com.org.logistics.logship.util.CommonUtil.extractNumberFromId(shipmentRequest.getHandlerId(), com.org.logistics.logship.constants.Constants.HANDLER_PREFIX))")
    @Mapping(target = "toWarehouseId",
            expression = "java(com.org.logistics.logship.util.CommonUtil.extractNumberFromId(shipmentRequest.getToWarehouseId(), com.org.logistics.logship.constants.Constants.WAREHOUSE_PREFIX))")
    ShipmentMaster createRequestToShipmentMaster(CreateShipmentRequest shipmentRequest);

    @Mapping(target = "shipmentId",
            expression = "java(com.org.logistics.logship.util.CommonUtil.appendPrefixToId(shipmentMaster.getShipmentId(), com.org.logistics.logship.constants.Constants.SHIPMENT_PREFIX))")
    CreateShipmentResponse shipmentMasterToCreateResponse(ShipmentMaster shipmentMaster);

    AddShipmentOrdersResponse addShipOrderRequestToAddShipOrderResponse(AddShipmentOrdersRequest addShipmentOrdersRequest);
}
