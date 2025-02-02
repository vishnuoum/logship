package com.org.logistics.logship.mappers.mybatis;

import org.apache.ibatis.annotations.Mapper;

import java.util.Map;

@Mapper
public interface WarehouseQualityCheckTableMapper {

    void insertWarehouseQualityCheckStatus(Integer orderId, Integer handlerId, Integer warehouseId, Map<Integer, String> qualityMap);
}
