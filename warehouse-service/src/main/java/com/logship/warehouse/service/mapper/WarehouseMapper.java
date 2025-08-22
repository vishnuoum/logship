package com.logship.warehouse.service.mapper;

import com.logship.warehouse.service.controller.request.CreateWarehouseRequest;
import com.logship.warehouse.service.entity.ServingCode;
import com.logship.warehouse.service.entity.Warehouse;
import org.mapstruct.*;

import java.util.List;

@Mapper(componentModel = "spring")
public interface WarehouseMapper {

    @Mapping(source = "pinCodes", target = "servingCodes", qualifiedByName = "pinCodeToServiceCodeConverter")
    Warehouse requestToEntityMapper(CreateWarehouseRequest request);

    @Named("pinCodeToServiceCodeConverter")
    static List<ServingCode> pinCodeToServiceCodeConverter(List<String> pinCodes) {
        return pinCodes.stream().map(pinCode -> {
            ServingCode servingCode = new ServingCode();
            servingCode.setPinCode(pinCode);
            return servingCode;
        }).toList();
    }

    @AfterMapping
    default void setWarehouseReferenceForRequestToEntity(
            @MappingTarget Warehouse warehouse,
             CreateWarehouseRequest request
    ) {
        // Ensures this runs ONLY when requestToEntityMapper() is called
        if (warehouse.getServingCodes() != null) {
            warehouse.getServingCodes().forEach(sc -> sc.setWarehouse(warehouse));
        }
    }
}
