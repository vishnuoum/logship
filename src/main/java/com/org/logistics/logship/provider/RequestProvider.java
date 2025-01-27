package com.org.logistics.logship.provider;

import com.org.logistics.logship.provider.request.*;
import com.org.logistics.logship.provider.response.*;
import com.org.logistics.logship.service.OrderService;
import com.org.logistics.logship.service.QualityCheckService;
import com.org.logistics.logship.service.SenderService;
import com.org.logistics.logship.service.ShipmentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class RequestProvider {

    private final OrderService orderService;
    private final SenderService senderService;
    private final ShipmentService shipmentService;
    private final QualityCheckService qualityCheckService;

    RequestProvider(OrderService orderService, SenderService senderService, ShipmentService shipmentService, QualityCheckService qualityCheckService) {
        this.orderService = orderService;
        this.senderService = senderService;
        this.shipmentService = shipmentService;
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
    public ResponseEntity<HttpStatus> updateOrderStatus(@RequestBody UpdateOrderStatusRequest updateOrderStatusRequest) {
        orderService.updateOrderStatus(updateOrderStatusRequest);
        return new ResponseEntity<>(HttpStatus.OK);
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
    public ResponseEntity<CreateShipmentResponse> createShipment(@RequestBody CreateShipmentRequest shipmentRequest) {
        return shipmentService.createShipment(shipmentRequest);
    }

    @PostMapping("/addShipmentOrders")
    public ResponseEntity<AddShipmentOrdersResponse> addShipmentOrders(@RequestBody AddShipmentOrdersRequest addShipmentOrdersRequest) {
        return shipmentService.addShipmentOrders(addShipmentOrdersRequest);
    }

    @PutMapping("/endShipment/{shipmentId}")
    public ResponseEntity<HttpStatus> endShipment(@PathVariable("shipmentId") String shipmentId) {
        shipmentService.endShipment(shipmentId);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
