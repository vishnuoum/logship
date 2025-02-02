package com.org.logistics.logship.mappers.mybatis;

import com.org.logistics.logship.dto.QualityCheckObject;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;


@Mapper
public interface QualityCheckTableMapper {

    void insertNewQualityCheck(QualityCheckObject qualityCheckObject);

    List<QualityCheckObject> getAllQCForOrder(Integer orderId);
}
