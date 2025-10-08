package com.companyname.projectname.utils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class LogUtils {

    private static final Logger logger = LogManager.getLogger();

    private LogUtils() {
        // Private constructor to prevent instantiation
    }

    public static void info(String message) {
        logger.info(message);
    }

    public static void debug(String message) {
        logger.debug(message);
    }

    public static void error(String message) {
        logger.error(message);
    }

    public static void warn(String message) {
        logger.warn(message);
    }

    public static void log(String level, String message) {
        switch (level.toLowerCase()) {
            case "info":
                logger.info(message);
                break;
            case "debug":
                logger.debug(message);
                break;
            case "error":
                logger.error(message);
                break;
            case "warn":
                logger.warn(message);
                break;
            default:
                logger.info(message);
                break;
        }
    }
}
