package com.org.logistics.logship.mappers.mybatis;

import com.org.logistics.logship.dto.OrderDetails;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface OrderDetailsTableMapper {

    void insertOrderDetails(OrderDetails orderDetails);

    OrderDetails getOrderDetails(Integer orderId);
}
