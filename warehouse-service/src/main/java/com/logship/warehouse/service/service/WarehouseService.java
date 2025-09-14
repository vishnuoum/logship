package com.logship.warehouse.service.service;

import com.logship.warehouse.service.controller.request.AddToInventoryRequest;
import com.logship.warehouse.service.controller.request.CheckoutInventoryRequest;
import com.logship.warehouse.service.controller.request.CreateWarehouseRequest;
import com.logship.warehouse.service.entity.Inventory;
import com.logship.warehouse.service.entity.Warehouse;
import com.logship.warehouse.service.exception.ExceptionManager;
import com.logship.warehouse.service.logging.LogUtil;
import com.logship.warehouse.service.mapper.InventoryMapper;
import com.logship.warehouse.service.mapper.WarehouseMapper;
import com.logship.warehouse.service.repository.InventoryRepository;
import com.logship.warehouse.service.repository.ServingCodeRepository;
import com.logship.warehouse.service.repository.WarehouseRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class WarehouseService {

    private final InventoryMapper inventoryMapper;
    private final WarehouseMapper warehouseMapper;
    private final InventoryRepository inventoryRepository;
    private final WarehouseRepository warehouseRepository;
    private final ServingCodeRepository servingCodeRepository;

    public void createWarehouse(CreateWarehouseRequest request) {
        try {
            Warehouse warehouse = warehouseMapper.requestToEntityMapper(request);
            warehouseRepository.save(warehouse);
        } catch (Exception e) {
            LogUtil.printInfo(getClass(), "Error while creating warehouse");
            LogUtil.printError(e.getMessage());
            throw ExceptionManager.throwException(ExceptionManager.ERRORCODE.WAREHOUSE_CREATION_ERROR);
        }
    }

    public Boolean isWarehouseAvailable(String pinCode) {
        try {
            return servingCodeRepository.existsByPinCode(pinCode);
        } catch (Exception e) {
            LogUtil.printInfo(getClass(), "Error while fetching warehouse details");
            LogUtil.printError(e.getMessage());
            throw ExceptionManager.throwException(ExceptionManager.ERRORCODE.WAREHOUSE_FETCH_ERROR);
        }
    }

    public void addToInventory(AddToInventoryRequest addToInventoryRequest) {
        try {
            Inventory inventory = inventoryMapper.mapRequestToEntity(addToInventoryRequest);
            inventoryRepository.save(inventory);
        } catch (Exception e) {
            LogUtil.printInfo(getClass(), "Error while adding inventory to warehouse");
            LogUtil.printError(e.getMessage());
            throw ExceptionManager.throwException(ExceptionManager.ERRORCODE.INVENTORY_CREATION_ERROR);
        }
    }

    public void checkoutInventory(CheckoutInventoryRequest checkoutInventoryRequest) {
        try {
            inventoryRepository.checkoutInventory(checkoutInventoryRequest.getOrderId(),
                    Warehouse.builder().id(checkoutInventoryRequest.getWarehouseId()).build());
        } catch (Exception e) {
            LogUtil.printInfo(getClass(), "Error while checking out inventory from warehouse");
            LogUtil.printError(e.getMessage());
            throw ExceptionManager.throwException(ExceptionManager.ERRORCODE.INVENTORY_CREATION_ERROR);
        }
    }
}
