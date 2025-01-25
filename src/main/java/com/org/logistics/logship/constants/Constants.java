package com.org.logistics.logship.constants;

public class Constants {

    private Constants() {}

    public static final String USER_PREFIX = "U";
    public static final String ORDER_PREFIX = "O";
    public static final String HANDLER_PREFIX = "H";
    public static final String WAREHOUSE_PREFIX = "W";
    public static final String QUALITY_CHECK_PREFIX = "QC";

    public enum OrderStatus {
        OC("ORDER CREATED"), OP("ORDER PROCESSING"), IT("IN TRANSIT"), OD("ORDER DELIVERED"), ITW("IN TRANSIT WAREHOUSE");

        final String message;

        OrderStatus(String message) {
            this.message = message;
        }

        public String getMessage() {
            return message;
        }
    }
}
