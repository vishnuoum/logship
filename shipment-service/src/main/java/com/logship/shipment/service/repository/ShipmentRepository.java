package com.logship.shipment.service.repository;

import com.logship.shipment.service.entity.Shipment;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ShipmentRepository extends CrudRepository<Shipment, Long> {

    @Modifying
    @Query("Update Shipment s set s.startDate = now() where id = :shipmentId")
    void startShipment(@Param("shipmentId") Long shipmentId);

    @Modifying
    @Query("Update Shipment s set s.endDate = now() where id = :shipmentId")
    void endShipment(@Param("shipmentId") Long shipmentId);
}
