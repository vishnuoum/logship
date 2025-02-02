package com.org.logistics.logship.persistence.helper;

import com.org.logistics.logship.dto.SenderDetails;
import static com.org.logistics.logship.exception.ExceptionManager.ErrorCode;

import com.org.logistics.logship.exception.ExceptionManager;
import com.org.logistics.logship.logging.LoggerUtil;
import com.org.logistics.logship.mappers.mybatis.SenderDetailsTableMapper;
import org.springframework.stereotype.Component;

@Component
public class SenderHelper {

    private final SenderDetailsTableMapper senderDetailsTableMapper;

    SenderHelper(SenderDetailsTableMapper senderDetailsTableMapper) {
        this.senderDetailsTableMapper = senderDetailsTableMapper;
    }

    public void insertSenderDetails(SenderDetails senderDetails) {
        try {
            senderDetailsTableMapper.insertSenderDetails(senderDetails);
        } catch (Exception e) {
            LoggerUtil.printError("Error while storing Sender details in DB");
            LoggerUtil.printError(e.getMessage());
            throw ExceptionManager.exceptionFactory.generateException(ErrorCode.DB_WRITE_ERROR);
        }
    }
}
