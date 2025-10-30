package com.companyname.projectname.tests;

import com.companyname.projectname.base.BaseTest;
import com.companyname.projectname.pages.HomePage;
import com.companyname.projectname.pages.LoginPage;
import org.testng.Assert;
import org.testng.annotations.Test;

public class LoginTest extends BaseTest {

    @Test
    public void testSuccessfulLogin() {
        driver.get("http://your-application-url.com/login"); // TODO: Update with actual login URL

        LoginPage loginPage = new LoginPage(driver);
        loginPage.login("validUsername", "validPassword"); // TODO: Replace with actual valid credentials

        HomePage homePage = new HomePage(driver);
        Assert.assertTrue(homePage.isUserLoggedIn(), "User is not logged in successfully.");
        Assert.assertEquals(homePage.getWelcomeMessage(), "Welcome, validUsername!", "Welcome message is incorrect."); // TODO: Adjust expected welcome message
    }
}
