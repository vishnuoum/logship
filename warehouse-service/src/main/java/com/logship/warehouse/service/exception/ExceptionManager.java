package com.logship.warehouse.service.exception;

import org.springframework.http.HttpStatus;

import java.util.HashMap;
import java.util.Map;

public class ExceptionManager {

    private static final Map<String, WarehouseServiceException> exceptionMap;
    private ExceptionManager(){}

    public static class ERRORCODE {
        private ERRORCODE(){}
        public static final String UNEXPECTED_ERROR = "E1000";
        public static final String VALIDATION_ERROR = "E1001";
        public static final String ACCESS_DENIED_ERROR = "E1003";
        public static final String WAREHOUSE_CREATION_ERROR = "E1004";
        public static final String WAREHOUSE_FETCH_ERROR = "E1005";
        public static final String INVENTORY_CREATION_ERROR = "E1006";
        public static final String INVENTORY_CHECKOUT_ERROR = "E1007";
        public static final String EMPLOYEE_ADD_ERROR = "E1008";
        public static final String EMPLOYEE_FETCH_ERROR = "E1009";
    }

    public static class ERRORMESSAGE {
        private ERRORMESSAGE(){}
        public static final String UNEXPECTED_ERROR = "An unexpected error occurred";
        public static final String VALIDATION_ERROR = "Request Validation error: [";
        public static final String ACCESS_DENIED_ERROR = "Access denied to resource";
        public static final String WAREHOUSE_CREATION_ERROR = "Error creating the warehouse";
        public static final String WAREHOUSE_FETCH_ERROR = "Error while fetching details of warehouse";
        public static final String INVENTORY_CREATION_ERROR = "Error while creating the inventory";
        public static final String INVENTORY_CHECKOUT_ERROR = "Error while checking out inventory from warehouse";
        public static final String EMPLOYEE_ADD_ERROR = "Error while adding employee";
        public static final String EMPLOYEE_FETCH_ERROR = "Error while fetching employee details";
    }

    static {
        exceptionMap = new HashMap<>();
        exceptionMap.put(ERRORCODE.UNEXPECTED_ERROR, new WarehouseServiceException(ERRORCODE.UNEXPECTED_ERROR,
                ERRORMESSAGE.UNEXPECTED_ERROR, HttpStatus.INTERNAL_SERVER_ERROR));
        exceptionMap.put(ERRORCODE.ACCESS_DENIED_ERROR, new WarehouseServiceException(ERRORCODE.ACCESS_DENIED_ERROR,
                ERRORMESSAGE.ACCESS_DENIED_ERROR, HttpStatus.UNAUTHORIZED));
        exceptionMap.put(ERRORCODE.WAREHOUSE_CREATION_ERROR, new WarehouseServiceException(ERRORCODE.WAREHOUSE_CREATION_ERROR,
                ERRORMESSAGE.WAREHOUSE_CREATION_ERROR, HttpStatus.INTERNAL_SERVER_ERROR));
        exceptionMap.put(ERRORCODE.WAREHOUSE_FETCH_ERROR, new WarehouseServiceException(ERRORCODE.WAREHOUSE_FETCH_ERROR,
                ERRORMESSAGE.WAREHOUSE_FETCH_ERROR, HttpStatus.INTERNAL_SERVER_ERROR));
        exceptionMap.put(ERRORCODE.INVENTORY_CREATION_ERROR, new WarehouseServiceException(ERRORCODE.INVENTORY_CREATION_ERROR,
                ERRORMESSAGE.INVENTORY_CREATION_ERROR, HttpStatus.INTERNAL_SERVER_ERROR));
        exceptionMap.put(ERRORCODE.INVENTORY_CHECKOUT_ERROR, new WarehouseServiceException(ERRORCODE.INVENTORY_CHECKOUT_ERROR,
                ERRORMESSAGE.INVENTORY_CHECKOUT_ERROR, HttpStatus.INTERNAL_SERVER_ERROR));
        exceptionMap.put(ERRORCODE.EMPLOYEE_ADD_ERROR, new WarehouseServiceException(ERRORCODE.EMPLOYEE_ADD_ERROR,
                ERRORMESSAGE.EMPLOYEE_ADD_ERROR, HttpStatus.INTERNAL_SERVER_ERROR));
        exceptionMap.put(ERRORCODE.EMPLOYEE_FETCH_ERROR, new WarehouseServiceException(ERRORCODE.EMPLOYEE_FETCH_ERROR,
                ERRORMESSAGE.EMPLOYEE_FETCH_ERROR, HttpStatus.INTERNAL_SERVER_ERROR));
    }

    public static WarehouseServiceException throwException(String errorCode) {
        return exceptionMap.get(errorCode);
    }
}
