package com.snc.api;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyString;

class UserServiceTest {

    private BaseApiClient mockApiClient;
    private UserService userService;

    @BeforeEach
    void setUp() {
        mockApiClient = Mockito.mock(BaseApiClient.class);
        // We need to pass a dummy baseUrl to UserService constructor, as it creates BaseApiClient internally.
        // For testing, we'll mock the apiClient directly after instantiation.
        userService = new UserService("http://localhost:8080");
        // Replace the internally created apiClient with our mock
        try {
            java.lang.reflect.Field apiClientField = UserService.class.getDeclaredField("apiClient");
            apiClientField.setAccessible(true);
            apiClientField.set(userService, mockApiClient);
        } catch (NoSuchFieldException | IllegalAccessException e) {
            fail("Failed to inject mockApiClient: " + e.getMessage());
        }
    }

    @Test
    void createUser() throws IOException {
        UserService.User newUser = new UserService.User(null, "John Doe", "john.doe@example.com");
        String responseJson = "{\"id\":\"1\",\"name\":\"John Doe\",\"email\":\"john.doe@example.com\"}";
        Mockito.when(mockApiClient.post(anyString(), anyString())).thenReturn(responseJson);

        UserService.User createdUser = userService.createUser(newUser);

        assertNotNull(createdUser);
        assertEquals("1", createdUser.getId());
        assertEquals("John Doe", createdUser.getName());
        assertEquals("john.doe@example.com", createdUser.getEmail());
        Mockito.verify(mockApiClient).post(Mockito.eq("/users"), anyString());
    }

    @Test
    void getUserById() throws IOException {
        String responseJson = "{\"id\":\"1\",\"name\":\"John Doe\",\"email\":\"john.doe@example.com\"}";
        Mockito.when(mockApiClient.get(anyString())).thenReturn(responseJson);

        UserService.User user = userService.getUserById("1");

        assertNotNull(user);
        assertEquals("1", user.getId());
        assertEquals("John Doe", user.getName());
        Mockito.verify(mockApiClient).get("/users/1");
    }

    @Test
    void getAllUsers() throws IOException {
        String responseJson = "[{\"id\":\"1\",\"name\":\"John Doe\",\"email\":\"john.doe@example.com\"},{\"id\":\"2\",\"name\":\"Jane Doe\",\"email\":\"jane.doe@example.com\"}]";
        Mockito.when(mockApiClient.get(anyString())).thenReturn(responseJson);

        List<UserService.User> users = userService.getAllUsers();

        assertNotNull(users);
        assertEquals(2, users.size());
        assertEquals("John Doe", users.get(0).getName());
        assertEquals("Jane Doe", users.get(1).getName());
        Mockito.verify(mockApiClient).get("/users");
    }

    @Test
    void updateUser() throws IOException {
        UserService.User updatedUser = new UserService.User("1", "Johnathan Doe", "johnathan.doe@example.com");
        String responseJson = "{\"id\":\"1\",\"name\":\"Johnathan Doe\",\"email\":\"johnathan.doe@example.com\"}";
        Mockito.when(mockApiClient.put(anyString(), anyString())).thenReturn(responseJson);

        UserService.User result = userService.updateUser("1", updatedUser);

        assertNotNull(result);
        assertEquals("1", result.getId());
        assertEquals("Johnathan Doe", result.getName());
        Mockito.verify(mockApiClient).put(Mockito.eq("/users/1"), anyString());
    }

    @Test
    void deleteUser() throws IOException {
        Mockito.when(mockApiClient.delete(anyString())).thenReturn(""); // Delete usually returns empty or 204 No Content

        userService.deleteUser("1");

        Mockito.verify(mockApiClient).delete("/users/1");
    }
}
