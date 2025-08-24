package com.logship.shipment.service.repository;

import com.logship.shipment.service.entity.PickupSchedule;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface PickupScheduleRepository extends CrudRepository<PickupSchedule, Long> {

    @Modifying
    @Query("Update PickupSchedule p set p.endDate = now() where p.orderId = :orderId")
    void endPickupSchedule(@Param("orderId") UUID orderId);
}
