package com.org.logistics.logship.provider;

import com.org.logistics.logship.provider.request.AddQualityCheckRequest;
import com.org.logistics.logship.provider.request.PlaceOrderRequest;
import com.org.logistics.logship.provider.request.RegisterSenderRequest;
import com.org.logistics.logship.provider.response.AddQualityCheckResponse;
import com.org.logistics.logship.provider.response.PlaceOrderResponse;
import com.org.logistics.logship.provider.response.RegisterSenderResponse;
import com.org.logistics.logship.service.OrderService;
import com.org.logistics.logship.service.QualityCheckService;
import com.org.logistics.logship.service.SenderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class RequestProvider {

    private final OrderService orderService;
    private final SenderService senderService;
    private final QualityCheckService qualityCheckService;

    RequestProvider(OrderService orderService, SenderService senderService, QualityCheckService qualityCheckService) {
        this.orderService = orderService;
        this.senderService = senderService;
        this.qualityCheckService = qualityCheckService;
    }

    @PostMapping("/registerSender")
    public ResponseEntity<RegisterSenderResponse> registerSender(@RequestBody RegisterSenderRequest registerSenderRequest) {
        return senderService.registerSender(registerSenderRequest);
    }

    @PostMapping("/placeOrder")
    public ResponseEntity<PlaceOrderResponse> placeOrder(@RequestBody PlaceOrderRequest orderRequest) {
        return orderService.placeOrder(orderRequest);
    }

    @PostMapping("/addQualityCheck")
    public ResponseEntity<AddQualityCheckResponse> addQualityCheck(@RequestBody AddQualityCheckRequest addQualityCheckRequest) {
        return qualityCheckService.addQualityCheck(addQualityCheckRequest);
    }

    @PutMapping("/updateOrderStatus")
    public ResponseEntity<?> updateOrderStatus() {
        return null;
    }

    @PostMapping("/doQualityCheck")
    public ResponseEntity<?> doQualityCheck() {
        return null;
    }

    @GetMapping("/getAllQC/{orderId}")
    public ResponseEntity<?> getAllQC(@PathVariable("orderId") String orderId) {
        return null;
    }

    @PostMapping("/createShipment")
    public ResponseEntity<?> createShipment() {
        return null;
    }

    @PostMapping("/addShipmentOrders")
    public ResponseEntity<?> addShipmentOrders() {
        return null;
    }

    @GetMapping("/trackShipment")
    public ResponseEntity<?> trackShipment() {
        return null;
    }
}
