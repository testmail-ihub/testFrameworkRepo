package com.snc.core;
import org.json.JSONObject;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Service class responsible for checking the health of the system.
 */
public class HealthCheckService extends BaseService {

    /**
     * Checks the health of the system and returns a JSON object indicating the status.
     *
     * @return JSON object with system health status
     */
    public String checkSystemHealth() {
        JSONObject healthStatus = new JSONObject();
        healthStatus.put("status", "UP");
        healthStatus.put("timestamp", LocalDateTime.now().format(DateTimeFormatter.ISO_DATE_TIME));
        return healthStatus.toString();
    }
}
