package com.org.logistics.logship.service;

import com.org.logistics.logship.dto.SenderDetails;
import com.org.logistics.logship.mappers.mapstruct.SenderStructMapper;
import com.org.logistics.logship.persistence.helper.SenderHelper;
import com.org.logistics.logship.provider.request.RegisterSenderRequest;
import com.org.logistics.logship.provider.response.LogShipResponse;
import com.org.logistics.logship.provider.response.RegisterSenderResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class SenderService {

    private final SenderHelper senderHelper;
    private final SenderStructMapper senderStructMapper;

    SenderService(SenderHelper senderHelper, SenderStructMapper senderStructMapper) {
        this.senderHelper = senderHelper;
        this.senderStructMapper = senderStructMapper;
    }

    @Transactional
    public ResponseEntity<RegisterSenderResponse> registerSender(RegisterSenderRequest registerSenderRequest) {
        return LogShipResponse.ok(insertSenderDetails(registerSenderRequest));
    }

    private RegisterSenderResponse insertSenderDetails(RegisterSenderRequest registerSenderRequest) {
        SenderDetails senderDetails = senderStructMapper.requestToSenderDetails(registerSenderRequest);
        senderHelper.insertSenderDetails(senderDetails);
        return senderStructMapper.senderDetailsToResponse(senderDetails);
    }
}
