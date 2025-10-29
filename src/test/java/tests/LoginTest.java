package tests;

import com.companyname.projectname.base.BaseTest;
import org.testng.annotations.Test;
import pages.LoginPage;

public class LoginTest extends BaseTest {

    @Test
    public void testSuccessfulLogin() {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.navigateTo("http://example.com/login"); // Replace with actual login URL
        loginPage.login("testuser", "testpassword"); // Replace with actual credentials
        // Add assertions here to verify successful login
        // For example, check if a dashboard element is visible
    }
}