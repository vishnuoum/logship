package com.logship.shipment.service.controller;

import com.logship.shipment.service.controller.request.*;
import com.logship.shipment.service.service.DeliveryService;
import com.logship.shipment.service.service.PickupScheduleService;
import com.logship.shipment.service.service.ShipmentService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/shipment")
public class ShipmentController {


    private final DeliveryService deliveryService;
    private final ShipmentService shipmentService;
    private final PickupScheduleService pickupScheduleService;

    @PreAuthorize("hasAuthority('MANAGER')")
    @PostMapping("/schedulePickup")
    public ResponseEntity<Void> schedulePickup(@Valid @RequestBody SchedulePickupRequest schedulePickupRequest) {
        pickupScheduleService.schedulePickup(schedulePickupRequest);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PreAuthorize("hasAuthority('DRIVER')")
    @PostMapping("/pickup")
    public ResponseEntity<Void> pickup(@Valid @RequestBody PickupRequest pickupRequest) {
        pickupScheduleService.updatePickup(pickupRequest);
        return ResponseEntity.ok().build();
    }

    @PreAuthorize("hasAuthority('MANAGER')")
    @PostMapping("/createShipment")
    public ResponseEntity<Void> createShipment(@Valid @RequestBody CreateShipmentRequest createShipmentRequest) {
        shipmentService.createShipment(createShipmentRequest);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PreAuthorize("hasAuthority('MANAGER')")
    @PostMapping("/startShipment")
    public ResponseEntity<Void> startShipment(@Valid @RequestBody StartShipmentRequest startShipmentRequest) {
        shipmentService.startShipment(startShipmentRequest);
        return ResponseEntity.ok().build();
    }

    @PreAuthorize("hasAuthority('MANAGER')")
    @PostMapping("/endShipment")
    public ResponseEntity<Void> endShipment(@Valid @RequestBody EndShipmentRequest endShipmentRequest) {
        shipmentService.endShipment(endShipmentRequest);
        return ResponseEntity.ok().build();
    }

    @PreAuthorize("hasAuthority('MANAGER')")
    @PostMapping("/scheduleDelivery")
    public ResponseEntity<Void> scheduleDelivery(@Valid @RequestBody ScheduleDeliveryRequest scheduleDeliveryRequest) {
        deliveryService.scheduleDelivery(scheduleDeliveryRequest);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PreAuthorize("hasAuthority('DRIVER')")
    @PostMapping("/deliver")
    public ResponseEntity<Void> deliver(@Valid @RequestBody DeliveryRequest deliveryRequest) {
        deliveryService.updateDelivery(deliveryRequest);
        return ResponseEntity.ok().build();
    }
}
