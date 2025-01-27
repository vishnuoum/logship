package com.org.logistics.logship.persistence.helper;

import com.org.logistics.logship.dto.HandlerDetails;
import com.org.logistics.logship.exception.LogShipErrorResponse;
import com.org.logistics.logship.logging.LoggerUtil;
import com.org.logistics.logship.mappers.mybatis.HandlerDetailsTableMapper;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

@Component
public class HandlerHelper {

    private final HandlerDetailsTableMapper handlerDetailsTableMapper;

    HandlerHelper(HandlerDetailsTableMapper handlerDetailsTableMapper) {
        this.handlerDetailsTableMapper = handlerDetailsTableMapper;
    }

    public HandlerDetails getHandlerDetails(Integer handlerId) {
        try {
            return handlerDetailsTableMapper.getHandlerDetails(handlerId);
        } catch (Exception e) {
            LoggerUtil.printInfo(getClass(), e.getMessage());
            throw new LogShipErrorResponse("DB_ERROR", "Error while fetching handler details from DB", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
