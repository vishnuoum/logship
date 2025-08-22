package com.logship.warehouse.service.repository;

import com.logship.warehouse.service.entity.Warehouse;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WarehouseRepository extends CrudRepository<Warehouse, Long> {
}
