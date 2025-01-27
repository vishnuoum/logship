package com.org.logistics.logship.persistence.helper;

import com.org.logistics.logship.dto.OrderDetails;
import com.org.logistics.logship.dto.OrderStatus;
import com.org.logistics.logship.exception.LogShipErrorResponse;
import com.org.logistics.logship.logging.LoggerUtil;
import com.org.logistics.logship.mappers.mybatis.OrderDetailsTableMapper;
import com.org.logistics.logship.mappers.mybatis.OrderQualityCheckTableMapper;
import com.org.logistics.logship.mappers.mybatis.OrderStatusTableMapper;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class OrderHelper {

    private final OrderStatusTableMapper orderStatusTableMapper;
    private final OrderDetailsTableMapper orderDetailsTableMapper;
    private final OrderQualityCheckTableMapper orderQualityCheckTableMapper;

    OrderHelper(OrderStatusTableMapper orderStatusTableMapper, OrderDetailsTableMapper orderDetailsTableMapper, OrderQualityCheckTableMapper orderQualityCheckTableMapper) {
        this.orderStatusTableMapper = orderStatusTableMapper;
        this.orderDetailsTableMapper = orderDetailsTableMapper;
        this.orderQualityCheckTableMapper = orderQualityCheckTableMapper;
    }

    public void insertOrderDetails(OrderDetails orderDetails) {
        try {
            orderDetailsTableMapper.insertOrderDetails(orderDetails);
        } catch (Exception e) {
            LoggerUtil.printError(e.getMessage());
            throw new LogShipErrorResponse("DB_ERROR", "Error while storing order details in DB", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public void insertOrderQualityChecks(Integer orderId, List<Integer> qcIds) {
        try {
            orderQualityCheckTableMapper.insertOrderQualityChecks(orderId, qcIds);
        } catch (Exception e) {
            LoggerUtil.printError(e.getMessage());
            throw new LogShipErrorResponse("DB_ERROR", "Error while storing order quality check details in DB", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public void insertOrderStatus(OrderStatus orderStatus) {
        try {
            orderStatusTableMapper.insertOrderStatus(orderStatus);
        } catch (Exception e) {
            LoggerUtil.printError(e.getMessage());
            throw new LogShipErrorResponse("DB_ERROR", "Error while storing order status details in DB", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public void insertOrderStatus(String orderStatus, Integer handlerId, List<Integer> orderIds) {
        try {
            orderStatusTableMapper.insertOrderStatusBatch(orderStatus, handlerId, orderIds);
        } catch (Exception e) {
            LoggerUtil.printError(e.getMessage());
            throw new LogShipErrorResponse("DB_ERROR", "Error while storing order status details in DB", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public OrderDetails getOrderDetails(Integer orderId) {
        try {
            return orderDetailsTableMapper.getOrderDetails(orderId);
        } catch (Exception e) {
            LoggerUtil.printError(e.getMessage());
            throw new LogShipErrorResponse("DB_ERROR", "Error while fetching order details in DB", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
