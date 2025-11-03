package com.logship.tracker.service.controller;

import com.logship.tracker.service.service.TrackerService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@AllArgsConstructor
public class TrackController {

    private final TrackerService trackerService;

    @GetMapping("/getStatus/{orderId}")
    public ResponseEntity<String> getStatus(@PathVariable("orderId")UUID orderId) {
        return ResponseEntity.ok(trackerService.getOrderStatus(orderId));
    }
}
