package com.logship.shipment.service.service;

import com.logship.shipment.service.controller.request.ListPendingRequest;
import com.logship.shipment.service.controller.request.PickupRequest;
import com.logship.shipment.service.controller.request.SchedulePickupRequest;
import com.logship.shipment.service.controller.request.model.OrderDetails;
import com.logship.shipment.service.entity.PendingPickupOrder;
import com.logship.shipment.service.entity.PickupSchedule;
import com.logship.shipment.service.exception.ExceptionManager;
import com.logship.shipment.service.logging.LogUtil;
import com.logship.shipment.service.mapper.PickupScheduleMapper;
import com.logship.shipment.service.repository.PendingPickupOrderRepository;
import com.logship.shipment.service.repository.PickupScheduleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class PickupScheduleService {

    private final PickupScheduleMapper pickupScheduleMapper;
    private final PendingPickupOrderRepository pendingPickupOrderRepository;
    private final PickupScheduleRepository pickupScheduleRepository;

    public void schedulePickup(SchedulePickupRequest schedulePickupRequest) {
        try {
            List<PickupSchedule> pickupSchedules = pickupScheduleMapper.orderDetailsToPickupSchedule(schedulePickupRequest.getOrderDetails());
            pickupSchedules.forEach(pickupSchedule -> pickupSchedule.setDriverId(schedulePickupRequest.getDriverId()));
            pickupScheduleRepository.saveAll(pickupSchedules);
            pendingPickupOrderRepository.deleteByOrderIdIn(schedulePickupRequest.getOrderDetails().stream().map(OrderDetails::getOrderId).toList());
        } catch (Exception e) {
            LogUtil.printInfo(getClass(), "Error while saving pickup schedules");
            LogUtil.printError(e.getMessage());
            throw ExceptionManager.throwException(ExceptionManager.ERRORCODE.PICKUP_SCHEDULE_ERROR);
        }
    }

    public void updatePickup(PickupRequest pickupRequest) {
        try {
            pickupScheduleRepository.endPickupSchedule(pickupRequest.getOrderId());
        } catch (Exception e) {
            LogUtil.printInfo(getClass(), "Error while ending the pickup schedule");
            LogUtil.printError(e.getMessage());
            throw ExceptionManager.throwException(ExceptionManager.ERRORCODE.PICKUP_END_ERROR);
        }
    }

    public List<UUID> getPendingPickupOrders(ListPendingRequest listPendingRequest) {
        try {
            List<PendingPickupOrder> pendingOrders = pendingPickupOrderRepository.getPendingPickupOrdersInPinCode(listPendingRequest.getPinCodes());
            return pendingOrders.stream().map(PendingPickupOrder::getOrderId).toList();
        } catch (Exception e) {
            LogUtil.printInfo(getClass(), "Error while fetching pending pickup orders");
            LogUtil.printError(e.getMessage());
            throw ExceptionManager.throwException(ExceptionManager.ERRORCODE.PENDING_PICKUP_FETCH_ERROR);
        }
    }
}
