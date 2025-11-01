package com.snc.api;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.List;

public class UserService {

    private final BaseApiClient apiClient;
    private final Gson gson;
    private static final String USERS_ENDPOINT = "/users";

    public UserService(String baseUrl) {
        this.apiClient = new BaseApiClient(baseUrl);
        this.gson = new Gson();
    }

    public User createUser(User user) throws IOException {
        String json = gson.toJson(user);
        String response = apiClient.post(USERS_ENDPOINT, json);
        return gson.fromJson(response, User.class);
    }

    public User getUserById(String id) throws IOException {
        String response = apiClient.get(USERS_ENDPOINT + "/" + id);
        return gson.fromJson(response, User.class);
    }

    public List<User> getAllUsers() throws IOException {
        String response = apiClient.get(USERS_ENDPOINT);
        Type userListType = new TypeToken<List<User>>() {}.getType();
        return gson.fromJson(response, userListType);
    }

    public User updateUser(String id, User user) throws IOException {
        String json = gson.toJson(user);
        String response = apiClient.put(USERS_ENDPOINT + "/" + id, json);
        return gson.fromJson(response, User.class);
    }

    public void deleteUser(String id) throws IOException {
        apiClient.delete(USERS_ENDPOINT + "/" + id);
    }

    // Placeholder for User class - actual implementation would be in a separate file
    public static class User {
        private String id;
        private String name;
        private String email;

        public User(String id, String name, String email) {
            this.id = id;
            this.name = name;
            this.email = email;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        @Override
        public String toString() {
            return "User{" +
                   "id='" + id + '\'' +
                   ", name='" + name + '\'' +
                   ", email='" + email + '\'' +
                   '}';
        }
    }
}