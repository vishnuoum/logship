package com.org.logistics.logship.persistence.helper;

import com.org.logistics.logship.dto.HandlerDetails;
import com.org.logistics.logship.exception.ExceptionFactory;
import static com.org.logistics.logship.exception.ExceptionManager.ErrorCode;
import com.org.logistics.logship.logging.LoggerUtil;
import com.org.logistics.logship.mappers.mybatis.HandlerDetailsTableMapper;
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
            LoggerUtil.printError("Error while fetching handler details from DB");
            LoggerUtil.printError(e.getMessage());
            throw new ExceptionFactory().generateException(ErrorCode.DB_READ_ERROR);
        }
    }
}
