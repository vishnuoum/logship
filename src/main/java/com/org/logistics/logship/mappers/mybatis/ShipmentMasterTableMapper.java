package com.org.logistics.logship.mappers.mybatis;

import com.org.logistics.logship.dto.ShipmentMaster;
import org.apache.ibatis.annotations.Mapper;


@Mapper
public interface ShipmentMasterTableMapper {

    void insertShipment(ShipmentMaster shipmentMaster);

    ShipmentMaster getShipmentMaster(Integer shipmentId);

    void startShipment(Integer shipmentId);

    void endShipment(Integer shipmentId);
}
