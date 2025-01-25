package com.org.logistics.logship.service;

import com.org.logistics.logship.dao.QualityCheckObject;
import com.org.logistics.logship.mappers.mapstruct.QualityCheckStructMapper;
import com.org.logistics.logship.persistence.helper.QualityCheckHelper;
import com.org.logistics.logship.provider.request.AddQualityCheckRequest;
import com.org.logistics.logship.provider.response.AddQualityCheckResponse;
import com.org.logistics.logship.provider.response.LogShipResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class QualityCheckService {

    private final QualityCheckHelper qualityCheckHelper;
    private final QualityCheckStructMapper qualityCheckStructMapper;

    QualityCheckService(QualityCheckHelper qualityCheckHelper, QualityCheckStructMapper qualityCheckStructMapper) {
        this.qualityCheckHelper = qualityCheckHelper;
        this.qualityCheckStructMapper = qualityCheckStructMapper;
    }

    @Transactional
    public ResponseEntity<AddQualityCheckResponse> addQualityCheck(AddQualityCheckRequest addQualityCheckRequest) {
        return LogShipResponse.ok(addNewQualityCheck(addQualityCheckRequest));
    }

    private AddQualityCheckResponse addNewQualityCheck(AddQualityCheckRequest addQualityCheckRequest) {
        QualityCheckObject qualityCheckObject = qualityCheckStructMapper.requestToQualityCheckObject(addQualityCheckRequest);
        qualityCheckHelper.insertNewQualityCheck(qualityCheckObject);
        return qualityCheckStructMapper.qualityCheckObjectToAddQualityResponse(qualityCheckObject);
    }
}
