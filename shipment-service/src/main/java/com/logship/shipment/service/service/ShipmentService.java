package com.logship.shipment.service.service;

import com.logship.shipment.service.controller.request.CreateShipmentRequest;
import com.logship.shipment.service.controller.request.EndShipmentRequest;
import com.logship.shipment.service.controller.request.StartShipmentRequest;
import com.logship.shipment.service.exception.ExceptionManager;
import com.logship.shipment.service.logging.LogUtil;
import com.logship.shipment.service.mapper.ShipmentMapper;
import com.logship.shipment.service.repository.ShipmentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ShipmentService {

    private final ShipmentMapper shipmentMapper;
    private final ShipmentRepository shipmentRepository;

    public void createShipment(CreateShipmentRequest createShipmentRequest) {
        try {
            shipmentRepository.save(shipmentMapper.createRequestToEntity(createShipmentRequest));
        } catch (Exception e) {
            LogUtil.printInfo(getClass(), "Error while creating shipment");
            LogUtil.printError(e);
            throw ExceptionManager.throwException(ExceptionManager.ERRORCODE.SHIPMENT_CREATE_ERROR);
        }
    }

    public void startShipment(StartShipmentRequest startShipmentRequest) {
        try {
            shipmentRepository.startShipment(startShipmentRequest.getShipmentId());
        } catch (Exception e) {
            LogUtil.printInfo(getClass(), "Error while starting shipment");
            LogUtil.printError(e);
            throw ExceptionManager.throwException(ExceptionManager.ERRORCODE.SHIPMENT_START_ERROR);
        }
    }

    public void endShipment(EndShipmentRequest endShipmentRequest) {
        try {
            shipmentRepository.endShipment(endShipmentRequest.getShipmentId());
        } catch (Exception e) {
            LogUtil.printInfo(getClass(), "Error while ending shipment");
            LogUtil.printError(e);
            throw ExceptionManager.throwException(ExceptionManager.ERRORCODE.SHIPMENT_END_ERROR);
        }
    }
}
