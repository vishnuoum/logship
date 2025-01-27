package com.org.logistics.logship.mappers.mybatis;

import com.org.logistics.logship.dto.HandlerDetails;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface HandlerDetailsTableMapper {

    HandlerDetails getHandlerDetails(Integer handlerId);
}
