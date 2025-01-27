package com.org.logistics.logship.persistence.helper;

import com.org.logistics.logship.dto.ShipmentMaster;
import com.org.logistics.logship.exception.LogShipErrorResponse;
import com.org.logistics.logship.logging.LoggerUtil;
import com.org.logistics.logship.mappers.mybatis.ShipmentDetailsTableMapper;
import com.org.logistics.logship.mappers.mybatis.ShipmentMasterTableMapper;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ShipmentHelper {

    private final ShipmentMasterTableMapper shipmentMasterTableMapper;
    private final ShipmentDetailsTableMapper shipmentDetailsTableMapper;

    ShipmentHelper(ShipmentMasterTableMapper shipmentMasterTableMapper, ShipmentDetailsTableMapper shipmentDetailsTableMapper) {
        this.shipmentMasterTableMapper = shipmentMasterTableMapper;
        this.shipmentDetailsTableMapper = shipmentDetailsTableMapper;
    }

    public void createShipment(ShipmentMaster shipmentMaster) {
        try {
            shipmentMasterTableMapper.insertShipment(shipmentMaster);
        } catch (Exception e) {
            LoggerUtil.printError(e.getMessage());
            throw new LogShipErrorResponse("DB_ERROR", "Error while creating shipment", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public void addOrdersToShipment(Integer shipmentId, List<Integer> orders) {
        try {
            shipmentDetailsTableMapper.addOrdersToShipment(shipmentId, orders);
        } catch (Exception e) {
            LoggerUtil.printError(e.getMessage());
            throw new LogShipErrorResponse("DB_ERROR", "Error while adding orders to shipment", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public void endShipment(Integer shipmentId) {
        try {
            shipmentMasterTableMapper.endShipment(shipmentId);
        } catch (Exception e) {
            LoggerUtil.printError(e.getMessage());
            throw new LogShipErrorResponse("DB_ERROR", "Error while addings orders to shipment", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
