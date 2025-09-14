package com.logship.shipment.service.exception;

import org.springframework.http.HttpStatus;

import java.util.HashMap;
import java.util.Map;

public class ExceptionManager {

    private static final Map<String, ShipmentServiceException> exceptionMap;
    private ExceptionManager(){}

    public static class ERRORCODE {
        private ERRORCODE(){}
        public static final String UNEXPECTED_ERROR = "E1000";
        public static final String VALIDATION_ERROR = "E1001";
        public static final String ACCESS_DENIED_ERROR = "E1003";
        public static final String PICKUP_SCHEDULE_ERROR = "E1004";
        public static final String PICKUP_END_ERROR = "E1005";
        public static final String SHIPMENT_CREATE_ERROR = "E1006";
        public static final String SHIPMENT_START_ERROR = "E1007";
        public static final String SHIPMENT_END_ERROR = "E1008";
        public static final String DELIVERY_SCHEDULE_ERROR = "E1009";
        public static final String DELIVERY_END_ERROR = "E1010";
        public static final String PENDING_PICKUP_FETCH_ERROR = "E1011";
    }

    public static class ERRORMESSAGE {
        private ERRORMESSAGE(){}
        public static final String UNEXPECTED_ERROR = "An unexpected error occurred";
        public static final String VALIDATION_ERROR = "Request Validation error: [";
        public static final String ACCESS_DENIED_ERROR = "Access denied to resource";
        public static final String PICKUP_SCHEDULE_ERROR = "Error while saving the pickup schedule";
        public static final String PICKUP_END_ERROR = "Error while ending the pickup schedule";
        public static final String SHIPMENT_CREATE_ERROR = "Error while creating shipment";
        public static final String SHIPMENT_START_ERROR = "Error while starting shipment";
        public static final String SHIPMENT_END_ERROR = "Error while ending shipment";
        public static final String DELIVERY_SCHEDULE_ERROR = "Error while saving the delivery schedule";
        public static final String DELIVERY_END_ERROR = "Error while ending the delivery schedule";
        public static final String PENDING_PICKUP_FETCH_ERROR = "Error while fetching pending pickup orders";
    }

    static {
        exceptionMap = new HashMap<>();
        exceptionMap.put(ERRORCODE.UNEXPECTED_ERROR, new ShipmentServiceException(ERRORCODE.UNEXPECTED_ERROR,
                ERRORMESSAGE.UNEXPECTED_ERROR, HttpStatus.INTERNAL_SERVER_ERROR));
        exceptionMap.put(ERRORCODE.ACCESS_DENIED_ERROR, new ShipmentServiceException(ERRORCODE.ACCESS_DENIED_ERROR,
                ERRORMESSAGE.ACCESS_DENIED_ERROR, HttpStatus.UNAUTHORIZED));
        exceptionMap.put(ERRORCODE.PICKUP_SCHEDULE_ERROR, new ShipmentServiceException(ERRORCODE.PICKUP_SCHEDULE_ERROR,
                ERRORMESSAGE.PICKUP_SCHEDULE_ERROR, HttpStatus.INTERNAL_SERVER_ERROR));
        exceptionMap.put(ERRORCODE.PICKUP_END_ERROR, new ShipmentServiceException(ERRORCODE.PICKUP_END_ERROR,
                ERRORMESSAGE.PICKUP_END_ERROR, HttpStatus.INTERNAL_SERVER_ERROR));
        exceptionMap.put(ERRORCODE.SHIPMENT_CREATE_ERROR, new ShipmentServiceException(ERRORCODE.SHIPMENT_CREATE_ERROR,
                ERRORMESSAGE.SHIPMENT_CREATE_ERROR, HttpStatus.INTERNAL_SERVER_ERROR));
        exceptionMap.put(ERRORCODE.SHIPMENT_START_ERROR, new ShipmentServiceException(ERRORCODE.SHIPMENT_START_ERROR,
                ERRORMESSAGE.SHIPMENT_START_ERROR, HttpStatus.INTERNAL_SERVER_ERROR));
        exceptionMap.put(ERRORCODE.SHIPMENT_END_ERROR, new ShipmentServiceException(ERRORCODE.SHIPMENT_END_ERROR,
                ERRORMESSAGE.SHIPMENT_END_ERROR, HttpStatus.INTERNAL_SERVER_ERROR));
        exceptionMap.put(ERRORCODE.DELIVERY_SCHEDULE_ERROR, new ShipmentServiceException(ERRORCODE.DELIVERY_SCHEDULE_ERROR,
                ERRORMESSAGE.DELIVERY_SCHEDULE_ERROR, HttpStatus.INTERNAL_SERVER_ERROR));
        exceptionMap.put(ERRORCODE.DELIVERY_END_ERROR, new ShipmentServiceException(ERRORCODE.DELIVERY_END_ERROR,
                ERRORMESSAGE.DELIVERY_END_ERROR, HttpStatus.INTERNAL_SERVER_ERROR));
        exceptionMap.put(ERRORCODE.PENDING_PICKUP_FETCH_ERROR, new ShipmentServiceException(ERRORCODE.PENDING_PICKUP_FETCH_ERROR,
                ERRORMESSAGE.PENDING_PICKUP_FETCH_ERROR, HttpStatus.INTERNAL_SERVER_ERROR));
    }

    public static ShipmentServiceException throwException(String errorCode) {
        return exceptionMap.get(errorCode);
    }
}
