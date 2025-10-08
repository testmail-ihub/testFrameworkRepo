package com.companyname.projectname.utils;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
public class ScreenshotUtils {

    private static final String SCREENSHOT_DIR = "screenshots";
    private static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("yyyyMMddHHmmss");

    public static String captureScreenshot(WebDriver driver) {
        return captureScreenshot(driver, null);
    }

    public static String captureScreenshot(WebDriver driver, String testName) {
        try {
            File screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
            String filePath = getScreenshotPath(testName);
            FileUtils.copyFile(screenshot, new File(filePath));
            LogUtils.info("Screenshot saved at: " + filePath);
            return filePath;
        } catch (IOException e) {
            LogUtils.error("Failed to capture screenshot", e);
            return null;
        }
    }

    private static String getScreenshotPath(String testName) {
        String timestamp = DATE_FORMAT.format(new Date());
        String fileName = (testName != null && !testName.isEmpty()) ? testName : timestamp;
        return ConfigReader.getProperty("screenshot.dir") + File.separator + SCREENSHOT_DIR + File.separator + fileName + ".png";
    }
}
