package com.org.logistics.logship.provider;

import com.org.logistics.logship.provider.request.*;
import com.org.logistics.logship.provider.response.*;
import com.org.logistics.logship.service.OrderService;
import com.org.logistics.logship.service.QualityCheckService;
import com.org.logistics.logship.service.SenderService;
import com.org.logistics.logship.service.ShipmentService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Validated
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
    public ResponseEntity<RegisterSenderResponse> registerSender(@Valid @RequestBody RegisterSenderRequest registerSenderRequest) {
        return senderService.registerSender(registerSenderRequest);
    }

    @PostMapping("/placeOrder")
    public ResponseEntity<PlaceOrderResponse> placeOrder(@Valid @RequestBody PlaceOrderRequest orderRequest) {
        return orderService.placeOrder(orderRequest);
    }

    @PostMapping("/addQualityCheck")
    public ResponseEntity<AddQualityCheckResponse> addQualityCheck(@Valid @RequestBody AddQualityCheckRequest addQualityCheckRequest) {
        return qualityCheckService.addQualityCheck(addQualityCheckRequest);
    }

    @PutMapping("/updateOrderStatus")
    public ResponseEntity<HttpStatus> updateOrderStatus(@Valid @RequestBody UpdateOrderStatusRequest updateOrderStatusRequest) {
        orderService.updateOrderStatus(updateOrderStatusRequest);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("/updateQCStatus")
    public ResponseEntity<HttpStatus> doQualityCheck(@Valid @RequestBody UpdateQCStatusRequest updateQCStatusRequest) {
        qualityCheckService.updateQCStatus(updateQCStatusRequest);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/getAllQC/{orderId}")
    public ResponseEntity<GetAllQCResponse> getAllQC(@PathVariable("orderId")
                                                         @NotEmpty(message = "Order id should not be null or empty")
                                                         @Pattern(regexp = "^O\\d+$", message = "Shipment Id pattern not matched")
                                                         String orderId) {
        return qualityCheckService.getAllQCForOrder(orderId);
    }

    @PostMapping("/createShipment")
    public ResponseEntity<CreateShipmentResponse> createShipment(@Valid @RequestBody CreateShipmentRequest shipmentRequest) {
        return shipmentService.createShipment(shipmentRequest);
    }

    @PostMapping("/addShipmentOrders")
    public ResponseEntity<AddShipmentOrdersResponse> addShipmentOrders(@Valid @RequestBody AddShipmentOrdersRequest addShipmentOrdersRequest) {
        return shipmentService.addShipmentOrders(addShipmentOrdersRequest);
    }

    @PutMapping("/startShipment/{shipmentId}")
    public ResponseEntity<HttpStatus> startShipment(@PathVariable("shipmentId")
                                                        @NotEmpty(message = "Shipment id should not be null or empty")
                                                        @Pattern(regexp = "^S\\d+$", message = "Shipment Id pattern not matched")
                                                        String shipmentId) {
        shipmentService.startShipment(shipmentId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping("/endShipment/{shipmentId}")
    public ResponseEntity<HttpStatus> endShipment(@PathVariable("shipmentId")
                                                      @NotEmpty(message = "Shipment id should not be null or empty")
                                                      @Pattern(regexp = "^S\\d+$", message = "Shipment Id pattern not matched")
                                                      String shipmentId) {
        shipmentService.endShipment(shipmentId);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
