package com.logship.warehouse.service.repository;

import com.logship.warehouse.service.entity.Inventory;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface InventoryRepository extends CrudRepository<Inventory, Long> {

    @Modifying
    @Query("Update inventory set exitTime = now() where orderId = :orderId and warehouseId = :warehouseId")
    void checkoutInventory(@Param("orderId") UUID orderId, @Param("warehouseId") Long warehouseId);
}
