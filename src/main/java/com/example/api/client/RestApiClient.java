package com.example.api.client;

import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.util.Map;

public class RestApiClient {

    private final String endpointUrl;
    private final Map<String, String> authentication;

    public RestApiClient(String endpointUrl, Map<String, String> authentication) {
        this.endpointUrl = endpointUrl;
        this.authentication = authentication;
    }

    public String get(String path) throws IOException {
        return executeRequest(new HttpGet(endpointUrl + path));
    }

    public String post(String path, String requestBody) throws IOException {
        HttpPost post = new HttpPost(endpointUrl + path);
        post.setHeader("Content-Type", "application/json");
        post.setEntity(new org.apache.http.entity.StringEntity(requestBody));
        return executeRequest(post);
    }

    private String executeRequest(HttpUriRequest request) throws IOException {
        try (CloseableHttpClient httpClient = HttpClients.createDefault()) {
            request.setHeader("Authorization", authentication.get("type") + " " + authentication.get("token"));
            try (CloseableHttpResponse response = httpClient.execute(request)) {
                return EntityUtils.toString(response.getEntity());
            }
        }
    }
}
