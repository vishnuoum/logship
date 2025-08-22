package com.logship.warehouse.service.mapper;

import com.logship.warehouse.service.controller.request.AddToInventoryRequest;
import com.logship.warehouse.service.entity.Inventory;
import com.logship.warehouse.service.entity.Warehouse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.Named;

@Mapper(componentModel = "spring")
public interface InventoryMapper {

    @Mapping(target = "warehouse", source = "warehouseId", qualifiedByName = "createWarehouseReference")
    Inventory mapRequestToEntity(AddToInventoryRequest addToInventoryRequest);

    @Named("createWarehouseReference")
    static Warehouse createWarehouseReference(Long warehouseId) {
        return Warehouse.builder().id(warehouseId).build();
    }
}
