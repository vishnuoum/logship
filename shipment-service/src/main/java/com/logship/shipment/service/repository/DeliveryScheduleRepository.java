package com.logship.shipment.service.repository;

import com.logship.shipment.service.entity.DeliverySchedule;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface DeliveryScheduleRepository extends CrudRepository<DeliverySchedule, Long> {

    @Modifying
    @Query("Update DeliverySchedule d set d.endDate = now() where d.orderId = :orderId")
    void endDeliverySchedule(@Param("orderId") UUID orderId);
}
