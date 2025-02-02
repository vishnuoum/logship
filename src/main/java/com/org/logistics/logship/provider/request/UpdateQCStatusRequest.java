package com.org.logistics.logship.provider.request;

import com.org.logistics.logship.provider.request.bo.QualityStatus;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class UpdateQCStatusRequest {

    private String orderId;
    private String handlerId;
    private List<QualityStatus> qualityStatusList;
}
