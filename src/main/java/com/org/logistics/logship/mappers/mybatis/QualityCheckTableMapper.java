package com.org.logistics.logship.mappers.mybatis;

import com.org.logistics.logship.dao.QualityCheckObject;
import org.apache.ibatis.annotations.Mapper;


@Mapper
public interface QualityCheckTableMapper {

    void insertNewQualityCheck(QualityCheckObject qualityCheckObject);
}
