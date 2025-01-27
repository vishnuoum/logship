package com.org.logistics.logship.mappers.mybatis;

import com.org.logistics.logship.dto.SenderDetails;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface SenderDetailsTableMapper {

    void insertSenderDetails(SenderDetails senderDetails);
}
