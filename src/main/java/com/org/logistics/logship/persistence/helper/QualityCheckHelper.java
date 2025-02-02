package com.org.logistics.logship.persistence.helper;

import com.org.logistics.logship.dto.QualityCheckObject;
import static com.org.logistics.logship.exception.ExceptionManager.ErrorCode;

import com.org.logistics.logship.exception.ExceptionManager;
import com.org.logistics.logship.logging.LoggerUtil;
import com.org.logistics.logship.mappers.mybatis.OrderQualityCheckTableMapper;
import com.org.logistics.logship.mappers.mybatis.QualityCheckTableMapper;
import com.org.logistics.logship.mappers.mybatis.WarehouseQualityCheckTableMapper;
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
            LoggerUtil.printError("Error while storing Quality Check details in DB");
            LoggerUtil.printError(e.getMessage());
            throw ExceptionManager.exceptionFactory.generateException(ErrorCode.DB_WRITE_ERROR);
        }
    }

    public void insertOrderQualityChecks(Integer orderId, List<Integer> qcIds) {
        try {
            orderQualityCheckTableMapper.insertOrderQualityChecks(orderId, qcIds);
        } catch (Exception e) {
            LoggerUtil.printError("Error while storing order quality check details in DB");
            LoggerUtil.printError(e.getMessage());
            throw ExceptionManager.exceptionFactory.generateException(ErrorCode.DB_WRITE_ERROR);
        }
    }

    public List<QualityCheckObject> getAllQCForOrder(Integer orderId) {
        try {
            return qualityCheckTableMapper.getAllQCForOrder(orderId);
        } catch (Exception e) {
            LoggerUtil.printError("Error while fetching Order Quality Check details from DB");
            LoggerUtil.printError(e.getMessage());
            throw ExceptionManager.exceptionFactory.generateException(ErrorCode.DB_READ_ERROR);
        }
    }

    public void updateQCStatus(Integer orderId, Integer handlerId, Integer warehouseId, Map<Integer, String> qualityMap) {
        try {
            warehouseQualityCheckTableMapper.insertWarehouseQualityCheckStatus(orderId, handlerId, warehouseId, qualityMap);
        } catch (Exception e) {
            LoggerUtil.printError("Error while updating Order Quality Check details from DB");
            LoggerUtil.printError(e.getMessage());
            throw ExceptionManager.exceptionFactory.generateException(ErrorCode.DB_WRITE_ERROR);
        }
    }
}
