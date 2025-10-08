package com.snc.core;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Service to check the health of the system.
 */
public class HealthCheckService extends BaseService {

    private final ObjectMapper objectMapper = new ObjectMapper();

    /**
     * Checks the health of the system and returns a JSON string representing the health status.
     *
     * @return a JSON string representing the health status
     */
    public String checkSystemHealth() {
        logger.info("Checking system health");

        // Create a health status object
        HealthStatus healthStatus = new HealthStatus("UP", LocalDateTime.now());

        try {
            // Convert the health status object to a JSON string
            return objectMapper.writeValueAsString(healthStatus);
        } catch (Exception e) {
            logger.error("Error converting health status to JSON", e);
            return "{\"status\": \"DOWN\", \"timestamp\": \"" + formatDateTime(LocalDateTime.now()) + "\"}";
        }
    }

    /**
     * Represents the health status of the system.
     */
    private static class HealthStatus {
        private final String status;
        private final LocalDateTime timestamp;

        public HealthStatus(String status, LocalDateTime timestamp) {
            this.status = status;
            this.timestamp = timestamp;
        }
    }
}
