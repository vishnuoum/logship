package com.logship.warehouse.service.repository;

import com.logship.warehouse.service.entity.Inventory;
import com.logship.warehouse.service.entity.Warehouse;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface InventoryRepository extends CrudRepository<Inventory, Long> {

    @Modifying
    @Query("Update Inventory i set i.exitTime = now() where i.orderId = :orderId and i.warehouse = :warehouse")
    void checkoutInventory(@Param("orderId") UUID orderId, @Param("warehouse") Warehouse warehouse);
}
