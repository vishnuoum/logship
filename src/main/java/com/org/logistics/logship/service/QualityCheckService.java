package com.org.logistics.logship.service;

import com.org.logistics.logship.helper.CommonHelper;
import com.org.logistics.logship.mappers.QualityCheckMapper;
import com.org.logistics.logship.provider.dto.response.QualityCheckDtoResponse;
import com.org.logistics.logship.provider.request.AddQualityCheckRequest;
import com.org.logistics.logship.provider.response.AddQualityCheckResponse;
import com.org.logistics.logship.provider.response.LogShipResponse;
import com.org.logistics.logship.util.CommonUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class QualityCheckService {

    private final Environment environment;
    private final CommonHelper commonHelper;
    private final QualityCheckMapper qualityCheckMapper;

    @Autowired
    QualityCheckService(CommonHelper commonHelper, QualityCheckMapper qualityCheckMapper, Environment environment) {
        this.environment = environment;
        this.commonHelper = commonHelper;
        this.qualityCheckMapper = qualityCheckMapper;
    }

    public ResponseEntity<AddQualityCheckResponse> addQualityCheck(AddQualityCheckRequest addQualityCheckRequest) {
        return LogShipResponse.ok(addNewQualityCheck(addQualityCheckRequest));
    }

    private AddQualityCheckResponse addNewQualityCheck(AddQualityCheckRequest addQualityCheckRequest) {
        String daoResponse = commonHelper.connectWithDataService(qualityCheckMapper.mapRequestToDtoRequest(addQualityCheckRequest), environment.getProperty("logship.data.endpoint.addqualitycheck"));
        return qualityCheckMapper.mapDtoResponseToResponse((QualityCheckDtoResponse) CommonUtil.convertJsonToObject(daoResponse, QualityCheckDtoResponse.class));
    }
}
