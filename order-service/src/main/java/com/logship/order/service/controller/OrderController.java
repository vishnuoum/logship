package com.logship.order.service.controller;

import com.logship.order.service.controller.request.CreateOrderRequest;
import com.logship.order.service.dto.OrderDTO;
import com.logship.order.service.service.OrderService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/order")
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;

    @PreAuthorize("hasAuthority('CUSTOMER')")
    @PostMapping("/create")
    public ResponseEntity<Void> createOrder(@Valid @RequestBody CreateOrderRequest request, @RequestHeader("X-UUID") String userId) {
        orderService.createOrder(request, userId);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PreAuthorize("hasAuthority('CUSTOMER')")
    @GetMapping("/fetchAllOrders")
    public ResponseEntity<List<OrderDTO>> fetchAllOrders(@RequestHeader("X-UUID") String userId) {
        return ResponseEntity.ok(orderService.fetchAllOrders(userId));
    }

    @PreAuthorize("hasAuthority('CUSTOMER')")
    @GetMapping("/getOrderDetails/{orderId}")
    public ResponseEntity<OrderDTO> getOrderDetails(@PathVariable UUID orderId) {
        return ResponseEntity.ok(orderService.getOrderDetails(orderId));
    }
}
