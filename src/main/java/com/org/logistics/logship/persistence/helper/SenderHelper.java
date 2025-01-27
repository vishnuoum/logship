package com.org.logistics.logship.persistence.helper;

import com.org.logistics.logship.dto.SenderDetails;
import com.org.logistics.logship.exception.LogShipErrorResponse;
import com.org.logistics.logship.logging.LoggerUtil;
import com.org.logistics.logship.mappers.mybatis.SenderDetailsTableMapper;
import org.springframework.http.HttpStatus;
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
            LoggerUtil.printError(e.getMessage());
            throw new LogShipErrorResponse("DB_ERROR", "Error while storing Sender details in DB", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
