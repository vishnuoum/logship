package com.logship.warehouse.service.mapper;

import com.logship.warehouse.service.controller.request.AddEmployeeRequest;
import com.logship.warehouse.service.dto.WarehouseEmployeeDTO;
import com.logship.warehouse.service.entity.Warehouse;
import com.logship.warehouse.service.entity.WarehouseEmployee;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.util.List;

@Mapper(componentModel = "spring")
public interface WarehouseEmployeeMapper {

    @Mapping(target = "warehouse", source = "warehouseId", qualifiedByName = "createWarehouseReference")
    WarehouseEmployee requestToEntityMapper(AddEmployeeRequest addEmployeeRequest);

    @Named("createWarehouseReference")
    static Warehouse createWarehouseReference(Long warehouseId) {
        return Warehouse.builder().id(warehouseId).build();
    }

    List<WarehouseEmployeeDTO> entityToDTOMapper(List<WarehouseEmployee> employees);
}
