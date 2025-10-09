package com.snc.core;
import org.json.JSONObject;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
public class HealthCheckServiceTest {

    @InjectMocks
    private HealthCheckService healthCheckService;

    @Test
    public void testCheckSystemHealth() {
        String healthStatus = healthCheckService.checkSystemHealth();
        JSONObject jsonObject = new JSONObject(healthStatus);

        assertNotNull(jsonObject);
        assertTrue(jsonObject.has("status"));
        assertTrue(jsonObject.has("timestamp"));
        assertEquals("UP", jsonObject.get("status"));
    }
}
