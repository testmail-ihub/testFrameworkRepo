package com.snc.api;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;
import java.io.IOException;
import java.util.Map;
public class UserService {

    private final BaseApiClient baseApiClient;

    public UserService(BaseApiClient baseApiClient) {
        this.baseApiClient = baseApiClient;
    }

    public String createUser(Map<String, String> userData) throws IOException {
        HttpPost post = new HttpPost("https://api.example.com/users");
        post.setEntity(new StringEntity(new JSONObject(userData).toString()));
        CloseableHttpResponse response = baseApiClient.getHttpClient().execute(post);
        try {
            int statusCode = response.getStatusLine().getStatusCode();
            if (statusCode == 201) {
                return EntityUtils.toString(response.getEntity());
            } else {
                throw new IOException("Failed to create user. Status code: " + statusCode);
            }
        } finally {
            response.close();
        }
    }

    public String getUser(String userId) throws IOException {
        HttpGet get = new HttpGet("https://api.example.com/users/" + userId);
        CloseableHttpResponse response = baseApiClient.getHttpClient().execute(get);
        try {
            int statusCode = response.getStatusLine().getStatusCode();
            if (statusCode == 200) {
                return EntityUtils.toString(response.getEntity());
            } else {
                throw new IOException("Failed to retrieve user. Status code: " + statusCode);
            }
        } finally {
            response.close();
        }
    }

    public String updateUser(String userId, Map<String, String> userData) throws IOException {
        HttpPut put = new HttpPut("https://api.example.com/users/" + userId);
        put.setEntity(new StringEntity(new JSONObject(userData).toString()));
        CloseableHttpResponse response = baseApiClient.getHttpClient().execute(put);
        try {
            int statusCode = response.getStatusLine().getStatusCode();
            if (statusCode == 200) {
                return EntityUtils.toString(response.getEntity());
            } else {
                throw new IOException("Failed to update user. Status code: " + statusCode);
            }
        } finally {
            response.close();
        }
    }

    public String deleteUser(String userId) throws IOException {
        HttpDelete delete = new HttpDelete("https://api.example.com/users/" + userId);
        CloseableHttpResponse response = baseApiClient.getHttpClient().execute(delete);
        try {
            int statusCode = response.getStatusLine().getStatusCode();
            if (statusCode == 204) {
                return "User deleted successfully.";
            } else {
                throw new IOException("Failed to delete user. Status code: " + statusCode);
            }
        } finally {
            response.close();
        }
    }
}
