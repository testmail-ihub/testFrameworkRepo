package com.example.api.client;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;
import java.util.Base64;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class RestApiClientTest {

    @Mock
    private RestTemplate restTemplate;

    @InjectMocks
    private RestApiClient restApiClient;

    private static final String API_KEY = "your_api_key";
    private static final String API_SECRET = "your_api_secret";
    private static final String AUTH_HEADER = "Basic " + Base64.getEncoder().encodeToString((API_KEY + ":" + API_SECRET).getBytes());

    @BeforeEach
    public void setup() {
        restApiClient.setApiKey(API_KEY);
        restApiClient.setApiSecret(API_SECRET);
    }

    @Test
    public void testGetMethod_Success() {
        String apiUrl = "https://api.example.com/data";
        String expectedResponse = "{\"data\":\"example\"}";

        when(restTemplate.getForObject(anyString(), any(Class.class))).thenReturn(expectedResponse);

        String response = restApiClient.get(apiUrl, String.class);

        assertEquals(expectedResponse, response);
    }

    @Test
    public void testGetMethod_Failure() {
        String apiUrl = "https://api.example.com/data";

        when(restTemplate.getForObject(anyString(), any(Class.class))).thenThrow(new RuntimeException("API error"));

        assertThrows(RuntimeException.class, () -> restApiClient.get(apiUrl, String.class));
    }

    @Test
    public void testPostMethod_Success() {
        String apiUrl = "https://api.example.com/data";
        String requestBody = "{\"key\":\"value\"}";
        String expectedResponse = "{\"data\":\"example\"}";

        when(restTemplate.postForObject(anyString(), any(Object.class), any(Class.class))).thenReturn(expectedResponse);
        // Add assertions and logic as needed
    }
}
