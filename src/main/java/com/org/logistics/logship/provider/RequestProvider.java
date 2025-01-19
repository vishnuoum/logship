package com.org.logistics.logship.provider;

import com.org.logistics.logship.provider.request.AddQualityCheckRequest;
import com.org.logistics.logship.provider.request.PlaceOrderRequest;
import com.org.logistics.logship.provider.response.AddQualityCheckResponse;
import com.org.logistics.logship.provider.response.PlaceOrderResponse;
import com.org.logistics.logship.service.OrderService;
import com.org.logistics.logship.service.QualityCheckService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RequestProvider {

    private final OrderService placeOrderService;
    private final QualityCheckService qualityCheckService;

    @Autowired
    private RequestProvider(OrderService placeOrderService, QualityCheckService qualityCheckService) {
        this.placeOrderService = placeOrderService;
        this.qualityCheckService = qualityCheckService;
    }

    @PostMapping("/place")
    public ResponseEntity<PlaceOrderResponse> placeOrder(@RequestBody PlaceOrderRequest orderRequest) {
        return placeOrderService.placeOrder(orderRequest);
    }

    @PostMapping("/addQualityCheck")
    public ResponseEntity<AddQualityCheckResponse> addQualityCheck(@RequestBody AddQualityCheckRequest addQualityCheckRequest) {
        return qualityCheckService.addQualityCheck(addQualityCheckRequest);
    }
}
