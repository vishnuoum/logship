package com.logship.shipment.service.service;

import com.logship.shipment.service.controller.request.PickupRequest;
import com.logship.shipment.service.controller.request.SchedulePickupRequest;
import com.logship.shipment.service.controller.request.model.OrderDetails;
import com.logship.shipment.service.entity.PickupSchedule;
import com.logship.shipment.service.exception.ExceptionManager;
import com.logship.shipment.service.logging.LogUtil;
import com.logship.shipment.service.mapper.PickupScheduleMapper;
import com.logship.shipment.service.repository.PendingPickupRepository;
import com.logship.shipment.service.repository.PickupScheduleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PickupScheduleService {

    private final PickupScheduleMapper pickupScheduleMapper;
    private final PendingPickupRepository pendingPickupRepository;
    private final PickupScheduleRepository pickupScheduleRepository;

    public void schedulePickup(SchedulePickupRequest schedulePickupRequest) {
        try {
            List<PickupSchedule> pickupSchedules = pickupScheduleMapper.orderDetailsToPickupSchedule(schedulePickupRequest.getOrderDetails());
            pickupSchedules.forEach(pickupSchedule -> pickupSchedule.setDriverId(schedulePickupRequest.getDriverId()));
            pickupScheduleRepository.saveAll(pickupSchedules);
            pendingPickupRepository.deleteByOrderIdIn(schedulePickupRequest.getOrderDetails().stream().map(OrderDetails::getOrderId).toList());
        } catch (Exception e) {
            LogUtil.printInfo(getClass(), "Error while saving pickup schedules");
            LogUtil.printError(e);
            throw ExceptionManager.throwException(ExceptionManager.ERRORCODE.PICKUP_SCHEDULE_ERROR);
        }
    }

    public void updatePickup(PickupRequest pickupRequest) {
        try {
            pickupScheduleRepository.endPickupSchedule(pickupRequest.getOrderId());
        } catch (Exception e) {
            LogUtil.printInfo(getClass(), "Error while ending the pickup schedule");
            LogUtil.printError(e);
            throw ExceptionManager.throwException(ExceptionManager.ERRORCODE.PICKUP_END_ERROR);
        }
    }
}
