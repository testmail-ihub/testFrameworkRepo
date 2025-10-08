package com.snc.api;
import org.apache.http.HttpStatus;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest {

    @Mock
    private BaseApiClient baseApiClient;

    @InjectMocks
    private UserService userService;

    private Map<String, String> user;

    @BeforeEach
    public void setUp() {
        user = new HashMap<>();
        user.put("id", "1");
        user.put("name", "John Doe");
        user.put("email", "john.doe@example.com");
    }

    @Test
    public void testCreateUser_Success() throws IOException {
        when(baseApiClient.doPost(anyString(), any(Map.class))).thenReturn(HttpStatus.SC_CREATED);

        int statusCode = userService.createUser(user);

        assertEquals(HttpStatus.SC_CREATED, statusCode);
    }

    @Test
    public void testCreateUser_Failure() throws IOException {
        when(baseApiClient.doPost(anyString(), any(Map.class))).thenReturn(HttpStatus.SC_INTERNAL_SERVER_ERROR);

        assertThrows(IOException.class, () -> userService.createUser(user));
    }

    @Test
    public void testGetUser_Success() throws IOException {
        when(baseApiClient.doGet(anyString())).thenReturn(HttpStatus.SC_OK);

        int statusCode = userService.getUser("1");

        assertEquals(HttpStatus.SC_OK, statusCode);
    }

    @Test
    public void testGetUser_Failure() throws IOException {
        when(baseApiClient.doGet(anyString())).thenReturn(HttpStatus.SC_NOT_FOUND);

        assertThrows(IOException.class, () -> userService.getUser("1"));
    }

    @Test
    public void testUpdateUser_Success() throws IOException {
        when(baseApiClient.doPut(anyString(), any(Map.class))).thenReturn(HttpStatus.SC_OK);

        int statusCode = userService.updateUser("1", user);

        assertEquals(HttpStatus.SC_OK, statusCode);
    }

    @Test
    public void testUpdateUser_Failure() throws IOException {
        when(baseApiClient.doPut(anyString(), any(Map.class))).thenReturn(HttpStatus.SC_BAD_REQUEST);

        assertThrows(IOException.class, () -> userService.updateUser("1", user));
    }

    @Test
    public void testDeleteUser_Success() throws IOException {
        when(baseApiClient.doDelete(anyString())).thenReturn(HttpStatus.SC_NO_CONTENT);

        int statusCode = userService.deleteUser("1");

        assertEquals(HttpStatus.SC_NO_CONTENT, statusCode);
    }

    @Test
    public void testDeleteUser_Failure() throws IOException {
        when(baseApiClient.doDelete(anyString())).thenReturn(HttpStatus.SC_INTERNAL_SERVER_ERROR);

        assertThrows(IOException.class, () -> userService.deleteUser("1"));
    }
}
