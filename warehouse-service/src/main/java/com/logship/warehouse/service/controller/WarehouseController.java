package com.logship.warehouse.service.controller;

import com.logship.warehouse.service.controller.request.AddToInventoryRequest;
import com.logship.warehouse.service.controller.request.CheckoutInventoryRequest;
import com.logship.warehouse.service.controller.request.CreateWarehouseRequest;
import com.logship.warehouse.service.service.WarehouseService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/warehouse")
public class WarehouseController {

    private final WarehouseService warehouseService;

    @PreAuthorize("hasAuthority('ADMIN', 'MANAGER')")
    @PostMapping("/create")
    public ResponseEntity<Void> createWarehouse(@RequestBody CreateWarehouseRequest createWarehouseRequest) {
        warehouseService.createWarehouse(createWarehouseRequest);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping("/isAvailable/{pinCode}")
    public ResponseEntity<Boolean> isWarehouseAvailable(@PathVariable("pinCode") Integer pinCode) {
        return ResponseEntity.ok(warehouseService.isWarehouseAvailable(pinCode));
    }

    @PreAuthorize("hasAuthority('MANAGER')")
    @PostMapping("/addToInventory")
    public ResponseEntity<Void> addToInventory(AddToInventoryRequest addToInventoryRequest) {
        warehouseService.addToInventory(addToInventoryRequest);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PreAuthorize("hasAuthority('MANAGER')")
    @PostMapping("/checkoutInventory")
    public ResponseEntity<Void> addToInventory(CheckoutInventoryRequest checkoutInventoryRequest) {
        warehouseService.checkoutInventory(checkoutInventoryRequest);
        return ResponseEntity.ok().build();
    }
}
