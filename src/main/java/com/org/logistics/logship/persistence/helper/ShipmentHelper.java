package com.org.logistics.logship.persistence.helper;

import com.org.logistics.logship.dto.ShipmentDetails;
import com.org.logistics.logship.dto.ShipmentMaster;
import static com.org.logistics.logship.exception.ExceptionManager.ErrorCode;

import com.org.logistics.logship.exception.ExceptionManager;
import com.org.logistics.logship.logging.LoggerUtil;
import com.org.logistics.logship.mappers.mybatis.ShipmentDetailsTableMapper;
import com.org.logistics.logship.mappers.mybatis.ShipmentMasterTableMapper;
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
            LoggerUtil.printError("Error while creating shipment");
            LoggerUtil.printError(e.getMessage());
            throw ExceptionManager.exceptionFactory.generateException(ErrorCode.DB_WRITE_ERROR);
        }
    }

    public void addOrdersToShipment(Integer shipmentId, List<Integer> orders) {
        try {
            shipmentDetailsTableMapper.addOrdersToShipment(shipmentId, orders);
        } catch (Exception e) {
            LoggerUtil.printError("Error while adding orders to shipment");
            LoggerUtil.printError(e.getMessage());
            throw ExceptionManager.exceptionFactory.generateException(ErrorCode.DB_WRITE_ERROR);
        }
    }

    public void startShipment(Integer shipmentId) {
        try {
            shipmentMasterTableMapper.startShipment(shipmentId);
        } catch (Exception e) {
            LoggerUtil.printError("Error while updating the start time of shipment");
            LoggerUtil.printError(e.getMessage());
            throw ExceptionManager.exceptionFactory.generateException(ErrorCode.DB_WRITE_ERROR);
        }
    }

    public ShipmentMaster getShipmentMaster(Integer shipmentId) {
        try {
            return shipmentMasterTableMapper.getShipmentMaster(shipmentId);
        } catch (Exception e) {
            LoggerUtil.printError("Error while fetching shipment master details");
            LoggerUtil.printError(e.getMessage());
            throw ExceptionManager.exceptionFactory.generateException(ErrorCode.DB_READ_ERROR);
        }
    }

    public List<ShipmentDetails> getShipmentOrders(Integer shipmentId) {
        try {
            return shipmentDetailsTableMapper.getShipmentOrders(shipmentId);
        } catch (Exception e) {
            LoggerUtil.printError("Error while fetching shipment orders from DB");
            LoggerUtil.printError(e.getMessage());
            throw ExceptionManager.exceptionFactory.generateException(ErrorCode.DB_READ_ERROR);
        }
    }

    public void endShipment(Integer shipmentId) {
        try {
            shipmentMasterTableMapper.endShipment(shipmentId);
        } catch (Exception e) {
            LoggerUtil.printError("Error while ending shipment");
            LoggerUtil.printError(e.getMessage());
            throw ExceptionManager.exceptionFactory.generateException(ErrorCode.DB_WRITE_ERROR);
        }
    }

    public void updateTakeInDate(Integer shipmentId, Integer orderId) {
        try {
            shipmentDetailsTableMapper.updateTakeInDate(shipmentId, orderId);
        } catch (Exception e) {
            LoggerUtil.printError("Error while updating shipment take in date");
            LoggerUtil.printError(e.getMessage());
            throw ExceptionManager.exceptionFactory.generateException(ErrorCode.DB_WRITE_ERROR);
        }
    }
}
