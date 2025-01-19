package com.org.logistics.logship.logging;

import com.org.logistics.logship.exception.LogShipErrorResponse;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class LoggerUtil {

    private LoggerUtil() {}

    public static void printInfo(Class<?> clazz, Object message) {
        Logger logger = LogManager.getLogger(clazz);
        logger.info(message);
    }

    public static void printError(Object message) {
        Logger logger = LogManager.getLogger(LogShipErrorResponse.class);
        logger.error(message);
    }

}
