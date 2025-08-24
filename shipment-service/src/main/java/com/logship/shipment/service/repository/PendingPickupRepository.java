package com.logship.shipment.service.repository;

import com.logship.shipment.service.entity.PendingPickup;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface PendingPickupRepository extends CrudRepository<PendingPickup, Long> {

    void deleteByOrderIdIn(List<UUID> orderIds);
}
