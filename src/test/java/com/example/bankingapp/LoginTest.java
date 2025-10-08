package com.example.bankingapp;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import static org.testng.Assert.assertTrue;
import java.time.Duration;
public class LoginTest extends BaseTest {
    @Test
    public void testSuccessfulLogin() {
        WebDriver driver = getDriver();
        LoginPage loginPage = new LoginPage(driver);
        DashboardPage dashboardPage = new DashboardPage(driver);
        Properties props = new Properties();
        try {
            props.load(new FileInputStream("src/test/resources/creds.properties"));
        } catch (IOException e) {
            throw new RuntimeException("Failed to load credentials from properties file", e);
        }
        loginPage.navigate();
        loginPage.inputUsername(props.getProperty("username"));
        loginPage.inputPassword(props.getProperty("password"));
        loginPage.submit();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.urlContains("dashboard"));
        assertTrue(dashboardPage.isDisplayed(), "Dashboard page is not displayed after successful login");
    }
}