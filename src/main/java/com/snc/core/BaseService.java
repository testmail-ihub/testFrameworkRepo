package com.snc.core;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Base service class providing common utilities and logging functionality.
 */
public abstract class BaseService {

    protected final Logger logger = LoggerFactory.getLogger(this.getClass());

    /**
     * Formats the current timestamp into a string.
     *
     * @return Formatted timestamp string
     */
    protected String formatTimestamp() {
        return LocalDateTime.now().format(DateTimeFormatter.ISO_DATE_TIME);
    }

    /**
     * Checks if an object is null and handles it safely.
     *
     * @param obj Object to check
     * @return Object if not null, otherwise a default value or null
     */
    protected <T> T handleNull(T obj) {
        return obj != null ? obj : (T) getDefaultValue();
    }

    /**
     * Returns a default value for null handling.
     *
     * @return Default value
     */
    @SuppressWarnings("unchecked")
    private <T> T getDefaultValue() {
        return (T) "";
    }
}
