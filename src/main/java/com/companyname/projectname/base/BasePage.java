package com.companyname.projectname.base;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public abstract class BasePage {
    protected final WebDriver driver;
    protected final Logger logger = LogManager.getLogger(getClass());
    protected final WaitUtils waitUtils;

    protected BasePage(WebDriver driver) {
        this.driver = driver;
        this.waitUtils = new WaitUtils(driver);
    }

    protected void click(WebElement element) {
        waitUtils.waitUntilClickable(element);
        element.click();
        logger.info("Clicked on element: {}", element);
    }

    protected void sendKeys(WebElement element, String text) {
        waitUtils.waitUntilVisible(element);
        element.sendKeys(text);
        logger.info("Sent keys '{}' to element: {}", text, element);
    }

    protected String getText(WebElement element) {
        waitUtils.waitUntilVisible(element);
        return element.getText();
    }

    protected void navigateTo(String url) {
        driver.get(url);
        logger.info("Navigated to URL: {}", url);
    }

    protected WebElement findElement(By locator) {
        return waitUtils.waitUntilPresent(locator);
    }

    protected void waitFor(String locator) {
        waitUtils.waitUntilPresent(locator);
    }
}
