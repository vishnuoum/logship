package com.org.logistics.logship.mappers.mybatis;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface OrderQualityCheckTableMapper {

    void insertOrderQualityChecks(@Param("order_id") Integer orderId, @Param("quality_check_ids") List<Integer> qcIds);
}
