package com.org.logistics.logship.mappers.mybatis;

import com.org.logistics.logship.dao.OrderStatus;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface OrderStatusTableMapper {

    void insertOrderStatus(OrderStatus orderStatus);
}
