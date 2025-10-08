package com.snc.core;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import java.time.LocalDateTime;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@ExtendWith(MockitoExtension.class)
public class HealthCheckServiceTest {

    @InjectMocks
    private HealthCheckService healthCheckService;

    @Test
    public void testCheckSystemHealth() throws Exception {
        String healthCheckResponse = healthCheckService.checkSystemHealth();
        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode jsonNode = objectMapper.readTree(healthCheckResponse);

        assertEquals("UP", jsonNode.get("status").asText());
        assertTrue(LocalDateTime.parse(jsonNode.get("timestamp").asText()).isSupported());
    }
}
