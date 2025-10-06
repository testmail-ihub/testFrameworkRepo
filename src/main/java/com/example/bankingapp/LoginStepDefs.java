package com.example.bankingapp;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;
public class LoginStepDefs {

    private WebDriver driver;

    @Given("I am on the login page")
    public void navigateToLoginPage() {
        System.setProperty("webdriver.chrome.driver", "path/to/chromedriver");
        driver = new ChromeDriver();
        driver.get("https://example.com/login");
    }

    @When("I enter valid username and password")
    public void enterValidCredentials() {
        WebElement usernameField = driver.findElement(By.id("username"));
        usernameField.sendKeys("validUsername");

        WebElement passwordField = driver.findElement(By.id("password"));
        passwordField.sendKeys("validPassword");
    }

    @When("I click the login button")
    public void clickLoginButton() {
        WebElement loginButton = driver.findElement(By.id("loginButton"));
        loginButton.click();
    }

    @Then("I should be logged in successfully")
    public void verifySuccessfulLogin() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.urlContains("dashboard"));
    }

    @Then("I close the browser")
    public void closeBrowser() {
        driver.quit();
    }
}