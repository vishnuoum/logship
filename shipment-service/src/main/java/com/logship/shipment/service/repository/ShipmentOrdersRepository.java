package com.logship.shipment.service.repository;

import com.logship.shipment.service.entity.ShipmentOrders;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ShipmentOrdersRepository extends CrudRepository<ShipmentOrders, Long> {

}
