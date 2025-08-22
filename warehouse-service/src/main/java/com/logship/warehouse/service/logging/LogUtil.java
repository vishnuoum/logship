package com.logship.warehouse.service.logging;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LogUtil {

    private LogUtil() {}

    public static void printInfo(Class clazz, Object message) {
        Logger logger = LoggerFactory.getLogger(clazz);
        logger.info(message.toString());
    }

    public static void printError(Object message) {
        Logger logger = LoggerFactory.getLogger(LogUtil.class);
        logger.error(message.toString());
    }
}
