package com.companyname.projectname.drivers;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import io.github.bonigarcia.wdm.WebDriverManager;
import com.companyname.projectname.config.ConfigReader;
public class DriverFactory {

    private static final Logger LOGGER = LogManager.getLogger(DriverFactory.class);

    private static ThreadLocal<WebDriver> driver = new ThreadLocal<>();

    public static WebDriver getDriver() {
        if (driver.get() == null) {
            String browser = ConfigReader.getProperty("browser");
            LOGGER.info("Initializing WebDriver for browser: {}", browser);

            switch (browser.toLowerCase()) {
                case "chrome":
                    WebDriverManager.chromedriver().setup();
                    driver.set(new ChromeDriver());
                    break;
                case "firefox":
                    WebDriverManager.firefoxdriver().setup();
                    driver.set(new FirefoxDriver());
                    break;
                case "edge":
                    WebDriverManager.edgedriver().setup();
                    driver.set(new EdgeDriver());
                    break;
                default:
                    LOGGER.error("Unsupported browser: {}", browser);
                    throw new UnsupportedOperationException("Unsupported browser: " + browser);
            }

            getDriver().manage().window().maximize();
        }
        return driver.get();
    }

    public static void quitDriver() {
        if (driver.get() != null) {
            LOGGER.info("Quitting WebDriver");
            driver.get().quit();
            driver.remove();
        }
    }
}
