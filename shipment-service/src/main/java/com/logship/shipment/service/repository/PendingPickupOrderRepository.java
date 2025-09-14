package com.logship.shipment.service.repository;

import com.logship.shipment.service.entity.PendingPickupOrder;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface PendingPickupOrderRepository extends CrudRepository<PendingPickupOrder, Long> {

    void deleteByOrderIdIn(List<UUID> orderIds);

    @Query("SELECT p FROM PendingPickupOrder p WHERE pickupPinCode IN :pinCodes")
    List<PendingPickupOrder> getPendingPickupOrdersInPinCode(@Param("pinCodes") List<String> pinCodes);
}
