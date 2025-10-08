package com.companyname.projectname.utils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;
public class WaitUtils {

    private static final Logger LOGGER = LogManager.getLogger(WaitUtils.class);
    private final WebDriver driver;
    private final int defaultTimeout;

    public WaitUtils(WebDriver driver, int defaultTimeout) {
        this.driver = driver;
        this.defaultTimeout = defaultTimeout;
    }

    public WebElement waitForPresenceOfElementLocated(By locator) {
        return waitForElement(ExpectedConditions.presenceOfElementLocated(locator), defaultTimeout);
    }

    public WebElement waitForVisibilityOfElementLocated(By locator) {
        return waitForElement(ExpectedConditions.visibilityOfElementLocated(locator), defaultTimeout);
    }

    public WebElement waitForClickabilityOfElementLocated(By locator) {
        return waitForElement(ExpectedConditions.elementToBeClickable(locator), defaultTimeout);
    }

    public WebElement waitForElement(ExpectedCondition<WebElement> condition, int timeout) {
        try {
            return new WebDriverWait(driver, Duration.ofSeconds(timeout)).until(condition);
        } catch (Exception e) {
            LOGGER.error("Element not found within the given timeout", e);
            throw new RuntimeException("Element not found within the given timeout", e);
        }
    }

    public WebElement waitForPresenceOfElementLocated(By locator, int timeout) {
        return waitForElement(ExpectedConditions.presenceOfElementLocated(locator), timeout);
    }

    public WebElement waitForVisibilityOfElementLocated(By locator, int timeout) {
        return waitForElement(ExpectedConditions.visibilityOfElementLocated(locator), timeout);
    }

    public WebElement waitForClickabilityOfElementLocated(By locator, int timeout) {
        return waitForElement(ExpectedConditions.elementToBeClickable(locator), timeout);
    }
}
