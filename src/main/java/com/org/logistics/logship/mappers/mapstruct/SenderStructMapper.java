package com.org.logistics.logship.mappers.mapstruct;

import com.org.logistics.logship.dao.SenderDetails;
import com.org.logistics.logship.provider.request.RegisterSenderRequest;
import com.org.logistics.logship.provider.response.RegisterSenderResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface SenderStructMapper {

    SenderDetails requestToSenderDetails(RegisterSenderRequest registerSenderRequest);

    @Mapping(target = "senderId",
            expression = "java(com.org.logistics.logship.util.CommonUtil.appendPrefixToId(senderDetails.getSenderId(), com.org.logistics.logship.constants.Constants.USER_PREFIX))")
    RegisterSenderResponse senderDetailsToResponse(SenderDetails senderDetails);
}
