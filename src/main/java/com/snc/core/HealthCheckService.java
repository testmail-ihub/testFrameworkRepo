package com.snc.core;

import org.json.JSONObject;

public class HealthCheckService extends BaseService {

    public String checkSystemHealth() {
        logger.info("Performing system health check...");
        JSONObject healthStatus = new JSONObject();
        healthStatus.put("status", "UP");
        healthStatus.put("timestamp", System.currentTimeMillis());
        healthStatus.put("service", "HealthCheckService");
        return healthStatus.toString();
    }
}
