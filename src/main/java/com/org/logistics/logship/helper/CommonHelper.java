package com.org.logistics.logship.helper;

import com.org.logistics.logship.exception.LogShipErrorResponse;
import com.org.logistics.logship.logging.LoggerUtil;
import com.org.logistics.logship.util.CommonUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;

@Component
public class CommonHelper {

    private final RestClient restClient;

    @Autowired
    CommonHelper(RestClient restClient) {
        this.restClient = restClient;
    }

    public String connectWithDataService(Object request, String endpoint) {
        try {
            LoggerUtil.printInfo(getClass(), "Data Service Endpoint: " + endpoint);
            LoggerUtil.printInfo(getClass(), "Data Service Request: " + CommonUtil.convertObjectToJson(request));
            String response =  restClient.post().uri(endpoint).body(request).retrieve().body(String.class);
            LoggerUtil.printInfo(getClass(), "Data Service Response: " + response);
            return response;
        } catch (Exception e) {
            throw new LogShipErrorResponse("DAO_ERROR", "Error while connecting with Data service", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
