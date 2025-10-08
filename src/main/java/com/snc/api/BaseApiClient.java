package com.snc.api;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Map;

import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

public class BaseApiClient {

    private final String baseUrl;
    private final CloseableHttpClient httpClient;

    public BaseApiClient(String baseUrl) {
        this.baseUrl = baseUrl;
        this.httpClient = HttpClients.createDefault();
    }

    public String get(String endpoint, Map<String, String> queryParams) throws IOException {
        HttpGet request = new HttpGet(getUri(endpoint, queryParams));
        return executeRequest(request);
    }

    public String post(String endpoint, String requestBody) throws IOException {
        HttpPost request = new HttpPost(getUri(endpoint));
        request.setEntity(new StringEntity(requestBody));
        return executeRequest(request);
    }

    public String put(String endpoint, String requestBody) throws IOException {
        HttpPut request = new HttpPut(getUri(endpoint));
        request.setEntity(new StringEntity(requestBody));
        return executeRequest(request);
    }

    public String delete(String endpoint) throws IOException {
        HttpDelete request = new HttpDelete(getUri(endpoint));
        return executeRequest(request);
    }

    private String getUri(String endpoint, Map<String, String> queryParams) {
        StringBuilder uri = new StringBuilder(baseUrl);
        uri.append(endpoint);

        if (queryParams != null && !queryParams.isEmpty()) {
            uri.append("?");
            int i = 0;
            for (Map.Entry<String, String> entry : queryParams.entrySet()) {
                if (i > 0) {
                    uri.append("&");
                }
                uri.append(entry.getKey()).append("=").append(entry.getValue());
                i++;
            }
        }

        return uri.toString();
    }

    private String getUri(String endpoint) {
        return baseUrl + endpoint;
    }

    private String executeRequest(/* HttpRequestBase request */) {
        // Implementation for executing the request and returning the response as String
        // (This is a placeholder; actual implementation should handle request execution and error handling)
        return null;
    }
}
