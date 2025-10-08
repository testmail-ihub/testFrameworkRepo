package com.companyname.projectname.base;

import com.companyname.projectname.config.ConfigurationManager;
import com.companyname.projectname.driver.DriverManager;
import com.companyname.projectname.driver.DriverType;
import com.companyname.projectname.reporter.ExtentTestManager;
import com.companyname.projectname.utils.Log;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import java.lang.reflect.Method;

public class BaseTest {

    protected WebDriver driver;

    @BeforeClass
    public void setUpClass() {
        // Initialize WebDriverManager
        WebDriverManager.webDriver().setup();

        // Load configuration
        ConfigurationManager.getInstance().loadConfig();

        // Initialize driver
        driver = DriverManager.getDriver(DriverType.CHROME);
    }

    @BeforeMethod
    public void startTest(Method method, Object[] params, ITestContext context) {
        // Start ExtentReports test
        ExtentTestManager.startTest(method.getName(), params);

        // Log test start
        Log.info("Starting test: " + method.getName());
    }

    @AfterMethod
    public void endTest(ITestResult result) {
        // Log test end
        Log.info("Ending test: " + result.getName());

        // End ExtentReports test
        ExtentTestManager.getTest().log(Status.getStatusFromTestAttribute(result));

        // Close driver if test failed
        if (result.getStatus() == ITestResult.FAILURE) {
            try {
                driver.quit();
            } catch (Exception e) {
                Log.error("Failed to quit driver", e);
            }
        }
    }

    @AfterClass
    public void tearDownClass() {
        // Quit driver
        try {
            driver.quit();
        } catch (Exception e) {
            Log.error("Failed to quit driver", e);
        }
    }
}
