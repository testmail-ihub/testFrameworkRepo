package com.snc.core;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Base class for all services, providing a logger and utility methods.
 */
public abstract class BaseService {

    protected final Logger logger = LoggerFactory.getLogger(getClass());

    /**
     * Formats a LocalDateTime object into a string using the ISO date-time format.
     *
     * @param dateTime the LocalDateTime object to format
     * @return the formatted date-time string
     */
    protected String formatDateTime(LocalDateTime dateTime) {
        return dateTime.format(DateTimeFormatter.ISO_DATE_TIME);
    }

    /**
     * Checks if an object is null and logs a warning if it is.
     *
     * @param obj the object to check
     * @return true if the object is not null, false otherwise
     */
    protected boolean checkNull(Object obj) {
        if (obj == null) {
            logger.warn("Null object passed to method");
            return false;
        }
        return true;
    }
}
