package com.org.logistics.logship.util;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.org.logistics.logship.logging.LoggerUtil;

public class CommonUtil {

    private CommonUtil() {}

    private static final ObjectMapper objectMapper;

    static {
        objectMapper = new ObjectMapper();
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
    }

    public static Object convertJsonToObject(String json, Class<?> clazz) {
        try {
            return objectMapper.readValue(json, clazz);
        } catch (Exception e) {
            LoggerUtil.printError(e);
            return null;
        }
    }

    public static String convertObjectToJson(Object data) {
        try {
            return objectMapper.writeValueAsString(data);
        } catch (Exception e) {
            return null;
        }
    }
}
