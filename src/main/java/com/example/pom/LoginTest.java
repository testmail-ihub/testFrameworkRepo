package com.example.pom;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
public class LoginTest extends BaseTest {

    @Test
    public void testSuccessfulLogin() {
        LoginPage loginPage = new LoginPage(driver);
        DashboardPage dashboardPage = new DashboardPage(driver);

        // Navigate to login page (assuming base URL is set in BaseTest)
        driver.get("https://example.com/login");

        // Perform login
        loginPage.login("testuser", "testpassword");

        // Verify user greeting on dashboard
        String expectedGreeting = "Welcome, testuser";
        String actualGreeting = dashboardPage.getUserGreeting();
        assertEquals(expectedGreeting, actualGreeting, "User greeting does not match expected value");
    }
}
