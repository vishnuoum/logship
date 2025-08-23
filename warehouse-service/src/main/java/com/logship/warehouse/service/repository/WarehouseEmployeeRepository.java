package com.logship.warehouse.service.repository;

import com.logship.warehouse.service.entity.Warehouse;
import com.logship.warehouse.service.entity.WarehouseEmployee;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface WarehouseEmployeeRepository extends CrudRepository<WarehouseEmployee, Long> {

    List<WarehouseEmployee> getWarehouseEmployeesByWarehouse(Warehouse warehouse);
}
