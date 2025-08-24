package com.logship.shipment.service.service;

import com.logship.shipment.service.controller.request.DeliveryRequest;
import com.logship.shipment.service.controller.request.ScheduleDeliveryRequest;
import com.logship.shipment.service.entity.DeliverySchedule;
import com.logship.shipment.service.exception.ExceptionManager;
import com.logship.shipment.service.logging.LogUtil;
import com.logship.shipment.service.mapper.DeliveryScheduleMapper;
import com.logship.shipment.service.repository.DeliveryScheduleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DeliveryService {

    private final DeliveryScheduleMapper deliveryScheduleMapper;
    private final DeliveryScheduleRepository deliveryScheduleRepository;

    public void scheduleDelivery(ScheduleDeliveryRequest scheduleDeliveryRequest) {
        try {
            List<DeliverySchedule> deliverySchedules = deliveryScheduleMapper.orderDetailsToDeliverySchedule(scheduleDeliveryRequest.getOrderDetails());
            deliverySchedules.forEach(deliverySchedule -> deliverySchedule.setOrderId(scheduleDeliveryRequest.getDriverId()));
            deliveryScheduleRepository.saveAll(deliverySchedules);
        } catch (Exception e) {
            LogUtil.printInfo(getClass(), "Error while saving the delivery schedule");
            LogUtil.printError(e);
            throw ExceptionManager.throwException(ExceptionManager.ERRORCODE.DELIVERY_SCHEDULE_ERROR);
        }
    }

    public void updateDelivery(DeliveryRequest deliveryRequest) {
        try {
            deliveryScheduleRepository.endDeliverySchedule(deliveryRequest.getOrderId());
        } catch (Exception e) {
            LogUtil.printInfo(getClass(), "Error while ending the delivery schedule");
            LogUtil.printError(e);
            throw ExceptionManager.throwException(ExceptionManager.ERRORCODE.DELIVERY_END_ERROR);
        }
    }
}
