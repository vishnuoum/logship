package com.logship.warehouse.service.controller;

import com.logship.warehouse.service.controller.request.AddEmployeeRequest;
import com.logship.warehouse.service.controller.request.AddToInventoryRequest;
import com.logship.warehouse.service.controller.request.CheckoutInventoryRequest;
import com.logship.warehouse.service.controller.request.CreateWarehouseRequest;
import com.logship.warehouse.service.dto.WarehouseEmployeeDTO;
import com.logship.warehouse.service.service.WarehouseEmployeeService;
import com.logship.warehouse.service.service.WarehouseService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/warehouse")
public class WarehouseController {

    private final WarehouseService warehouseService;
    private final WarehouseEmployeeService warehouseEmployeeService;

    @PreAuthorize("hasAuthority('ADMIN')")
    @PostMapping("/create")
    public ResponseEntity<Void> createWarehouse(@Valid @RequestBody CreateWarehouseRequest createWarehouseRequest) {
        warehouseService.createWarehouse(createWarehouseRequest);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PreAuthorize("hasAuthority('CUSTOMER')")
    @GetMapping("/isAvailable/{pinCode}")
    public ResponseEntity<Boolean> isWarehouseAvailable(@PathVariable("pinCode") String pinCode) {
        return ResponseEntity.ok(warehouseService.isWarehouseAvailable(pinCode));
    }

    @PreAuthorize("hasAuthority('MANAGER')")
    @PostMapping("/addToInventory")
    public ResponseEntity<Void> addToInventory(@Valid @RequestBody AddToInventoryRequest addToInventoryRequest) {
        warehouseService.addToInventory(addToInventoryRequest);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PreAuthorize("hasAuthority('MANAGER')")
    @PutMapping("/checkoutInventory")
    public ResponseEntity<Void> checkoutInventory(@Valid @RequestBody CheckoutInventoryRequest checkoutInventoryRequest) {
        warehouseService.checkoutInventory(checkoutInventoryRequest);
        return ResponseEntity.ok().build();
    }

    @PreAuthorize("hasAuthority('MANAGER', 'ADMIN')")
    @PostMapping("/addEmployee")
    public ResponseEntity<Void> addEmployee(@Valid @RequestBody AddEmployeeRequest addEmployeeRequest) {
        warehouseEmployeeService.addEmployee(addEmployeeRequest);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PreAuthorize("hasAuthority('MANAGER', 'ADMIN')")
    @GetMapping("/getEmployees/{warehouseId}")
    public ResponseEntity<List<WarehouseEmployeeDTO>> getEmployees(@PathVariable("warehouseId") Long warehouseId) {
        return ResponseEntity.ok(warehouseEmployeeService.getEmployees(warehouseId));
    }
}
