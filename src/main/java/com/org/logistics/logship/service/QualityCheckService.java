package com.org.logistics.logship.service;

import com.org.logistics.logship.constants.Constants;
import com.org.logistics.logship.dto.QualityCheckObject;
import com.org.logistics.logship.mappers.mapstruct.QualityCheckStructMapper;
import com.org.logistics.logship.persistence.helper.HandlerHelper;
import com.org.logistics.logship.persistence.helper.QualityCheckHelper;
import com.org.logistics.logship.provider.request.AddQualityCheckRequest;
import com.org.logistics.logship.provider.request.UpdateQCStatusRequest;
import com.org.logistics.logship.provider.request.bo.QualityStatus;
import com.org.logistics.logship.provider.response.AddQualityCheckResponse;
import com.org.logistics.logship.provider.response.GetAllQCResponse;
import com.org.logistics.logship.provider.response.LogShipResponse;
import com.org.logistics.logship.util.CommonUtil;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class QualityCheckService {

    private final HandlerHelper handlerHelper;
    private final QualityCheckHelper qualityCheckHelper;
    private final QualityCheckStructMapper qualityCheckStructMapper;

    QualityCheckService(HandlerHelper handlerHelper, QualityCheckHelper qualityCheckHelper, QualityCheckStructMapper qualityCheckStructMapper) {
        this.handlerHelper = handlerHelper;
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


    public ResponseEntity<GetAllQCResponse> getAllQCForOrder(String orderId) {
        return LogShipResponse.ok(getAllOrderQC(orderId));
    }

    private GetAllQCResponse getAllOrderQC(String orderId) {
        List<QualityCheckObject> qualityCheckObjects = qualityCheckHelper.getAllQCForOrder(CommonUtil.extractNumberFromId(orderId, Constants.ORDER_PREFIX));
        GetAllQCResponse getAllQCResponse = new GetAllQCResponse();
        getAllQCResponse.setQualityChecks(qualityCheckStructMapper.getModifiedQualityCheckObject(qualityCheckObjects));
        return getAllQCResponse;
    }

    public void updateQCStatus(UpdateQCStatusRequest updateQCStatusRequest) {
        updateOrderQCStatus(updateQCStatusRequest);
    }

    private void updateOrderQCStatus(UpdateQCStatusRequest updateQCStatusRequest) {
        Integer orderId = CommonUtil.extractNumberFromId(updateQCStatusRequest.getOrderId(), Constants.ORDER_PREFIX);
        Integer handlerId = CommonUtil.extractNumberFromId(updateQCStatusRequest.getHandlerId(), Constants.HANDLER_PREFIX);
        Map<Integer, String> qualityMap = updateQCStatusRequest.getQualityStatusList().stream()
                .collect(Collectors.toMap(qualityStatus -> CommonUtil.extractNumberFromId(qualityStatus.getQualityCheckId(),
                        Constants.QUALITY_CHECK_PREFIX), QualityStatus::getStatus));
        Optional.ofNullable(handlerHelper.getHandlerDetails(handlerId)).ifPresent(handlerDetails ->
                qualityCheckHelper.updateQCStatus(orderId, handlerId, handlerDetails.getWarehouseId(), qualityMap));

    }
}
