package com.org.logistics.logship.mappers.mybatis;

import com.org.logistics.logship.dto.ShipmentDetails;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface ShipmentDetailsTableMapper {

    void addOrdersToShipment(@Param("shipmentId") Integer shipmentId, @Param("orders") List<Integer> orders);

    List<ShipmentDetails> getShipmentOrders(Integer shipmentId);
}
