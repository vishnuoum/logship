package com.org.logistics.logship.mappers.mybatis;

import com.org.logistics.logship.dto.OrderStatus;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface OrderStatusTableMapper {

    void insertOrderStatus(OrderStatus orderStatus);

    void insertOrderStatusBatch(String orderStatus, Integer handlerId, List<Integer> orderIds);
}
