package com.org.logistics.logship.persistence.helper;

import com.org.logistics.logship.dto.OrderDetails;
import com.org.logistics.logship.dto.OrderStatus;
import com.org.logistics.logship.exception.ExceptionFactory;
import static com.org.logistics.logship.exception.ExceptionManager.ErrorCode;
import com.org.logistics.logship.exception.LogShipError;
import com.org.logistics.logship.logging.LoggerUtil;
import com.org.logistics.logship.mappers.mybatis.OrderDetailsTableMapper;
import com.org.logistics.logship.mappers.mybatis.OrderStatusTableMapper;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class OrderHelper {

    private final OrderStatusTableMapper orderStatusTableMapper;
    private final OrderDetailsTableMapper orderDetailsTableMapper;

    OrderHelper(OrderStatusTableMapper orderStatusTableMapper, OrderDetailsTableMapper orderDetailsTableMapper) {
        this.orderStatusTableMapper = orderStatusTableMapper;
        this.orderDetailsTableMapper = orderDetailsTableMapper;
    }

    public void insertOrderDetails(OrderDetails orderDetails) {
        try {
            orderDetailsTableMapper.insertOrderDetails(orderDetails);
        } catch (Exception e) {
            LoggerUtil.printError("Error while storing order details in DB");
            LoggerUtil.printError(e.getMessage());
            throw new ExceptionFactory().generateException(ErrorCode.DB_WRITE_ERROR);
        }
    }

    public void insertOrderStatus(OrderStatus orderStatus) {
        try {
            orderStatusTableMapper.insertOrderStatus(orderStatus);
        } catch (Exception e) {
            LoggerUtil.printError("Error while storing order status details in DB");
            LoggerUtil.printError(e.getMessage());

        }
    }

    public void insertOrderStatus(String orderStatus, Integer handlerId, List<Integer> orderIds) {
        try {
            orderStatusTableMapper.insertOrderStatusBatch(orderStatus, handlerId, orderIds);
        } catch (Exception e) {
            LoggerUtil.printError("Error while storing order status details in DB");
            LoggerUtil.printError(e.getMessage());
            throw new ExceptionFactory().generateException(ErrorCode.DB_WRITE_ERROR);
        }
    }

    public OrderDetails getOrderDetails(Integer orderId) {
        try {
            return orderDetailsTableMapper.getOrderDetails(orderId);
        } catch (Exception e) {
            LoggerUtil.printError("Error while fetching order details in DB");
            LoggerUtil.printError(e.getMessage());
            throw new ExceptionFactory().generateException(ErrorCode.DB_READ_ERROR);
        }
    }
}
