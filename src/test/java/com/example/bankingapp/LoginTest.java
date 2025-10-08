package com.example.bankingapp;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;
import java.time.Duration;
import static org.testng.Assert.assertTrue;
public class LoginTest extends BaseTest {
    @Test
    public void testSuccessfulLogin() {
        WebDriver driver = getDriver();
        LoginPage loginPage = new LoginPage(driver);
        DashboardPage dashboardPage = new DashboardPage(driver);
        loginPage.navigate();
        loginPage.inputUsername("validUsername");
        loginPage.inputPassword("validPassword");
        loginPage.submit();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.urlContains("dashboard"));
        assertTrue(dashboardPage.isDisplayed(), "Dashboard page is not displayed after successful login");
    }
}