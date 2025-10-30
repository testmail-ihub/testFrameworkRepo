package com.snc.core;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.json.JSONObject;

public class HealthCheckServiceTest {

    @Test
    void testCheckSystemHealth() {
        HealthCheckService healthCheckService = new HealthCheckService();
        String healthStatusJson = healthCheckService.checkSystemHealth();

        assertNotNull(healthStatusJson, "Health status should not be null");
        assertTrue(healthStatusJson.contains("status"), "Health status JSON should contain 'status'");
        assertTrue(healthStatusJson.contains("UP"), "Health status should be 'UP'");

        try {
            JSONObject jsonObject = new JSONObject(healthStatusJson);
            assertNotNull(jsonObject.get("timestamp"), "Timestamp should be present");
            assertNotNull(jsonObject.get("service"), "Service name should be present");
        } catch (Exception e) {
            // Handle JSON parsing exception if necessary
            assertTrue(false, "Failed to parse health status JSON: " + e.getMessage());
        }
    }
}
