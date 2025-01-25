package com.org.logistics.logship.mappers.mybatis;

import com.org.logistics.logship.dao.OrderDetails;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface OrderDetailsTableMapper {

    void insertOrderDetails(OrderDetails orderDetails);
}
