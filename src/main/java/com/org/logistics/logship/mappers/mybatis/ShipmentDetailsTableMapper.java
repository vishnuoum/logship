package com.org.logistics.logship.mappers.mybatis;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface ShipmentDetailsTableMapper {

    void addOrdersToShipment(@Param("shipmentId") Integer shipmentId, @Param("orders") List<Integer> orders);
}
