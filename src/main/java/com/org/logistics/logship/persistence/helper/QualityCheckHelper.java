package com.org.logistics.logship.persistence.helper;

import com.org.logistics.logship.dto.QualityCheckObject;
import com.org.logistics.logship.exception.LogShipErrorResponse;
import com.org.logistics.logship.logging.LoggerUtil;
import com.org.logistics.logship.mappers.mybatis.OrderQualityCheckTableMapper;
import com.org.logistics.logship.mappers.mybatis.QualityCheckTableMapper;
import com.org.logistics.logship.mappers.mybatis.WarehouseQualityCheckTableMapper;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

@Component
public class QualityCheckHelper {

    private final QualityCheckTableMapper qualityCheckTableMapper;
    private final OrderQualityCheckTableMapper orderQualityCheckTableMapper;
    private final WarehouseQualityCheckTableMapper warehouseQualityCheckTableMapper;

    QualityCheckHelper(QualityCheckTableMapper qualityCheckTableMapper, OrderQualityCheckTableMapper orderQualityCheckTableMapper, WarehouseQualityCheckTableMapper warehouseQualityCheckTableMapper) {
        this.qualityCheckTableMapper = qualityCheckTableMapper;
        this.orderQualityCheckTableMapper = orderQualityCheckTableMapper;
        this.warehouseQualityCheckTableMapper = warehouseQualityCheckTableMapper;
    }

    public void insertNewQualityCheck(QualityCheckObject qualityCheckObject) {
        try {
            qualityCheckTableMapper.insertNewQualityCheck(qualityCheckObject);
        } catch (Exception e) {
            LoggerUtil.printError(e.getMessage());
            throw new LogShipErrorResponse("DB_ERROR", "Error while storing Quality Check details in DB", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public void addOrderQualityChecks(Integer orderId, List<Integer> qualityCheckIds) {
        try {
            orderQualityCheckTableMapper.insertOrderQualityChecks(orderId, qualityCheckIds);
        } catch (Exception e) {
            LoggerUtil.printError(e.getMessage());
            throw new LogShipErrorResponse("DB_ERROR", "Error while storing Order Quality Check details in DB", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public List<QualityCheckObject> getAllQCForOrder(Integer orderId) {
        try {
            return qualityCheckTableMapper.getAllQCForOrder(orderId);
        } catch (Exception e) {
            LoggerUtil.printError(e.getMessage());
            throw new LogShipErrorResponse("DB_ERROR", "Error while fetching Order Quality Check details from DB", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public void updateQCStatus(Integer orderId, Integer handlerId, Integer warehouseId, Map<Integer, String> qualityMap) {
        try {
            warehouseQualityCheckTableMapper.insertWarehouseQualityCheckStatus(orderId, handlerId, warehouseId, qualityMap);
        } catch (Exception e) {
            LoggerUtil.printError(e.getMessage());
            throw new LogShipErrorResponse("DB_ERROR", "Error while fetching Order Quality Check details from DB", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
