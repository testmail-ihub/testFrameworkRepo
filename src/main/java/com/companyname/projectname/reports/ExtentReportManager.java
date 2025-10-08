package com.companyname.projectname.reports;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.ExtentHtmlReporterConfiguration;
import com.aventstack.extentreports.reporter.configuration.Theme;
import com.companyname.projectname.config.ConfigReader;
import com.companyname.projectname.utils.LogUtils;
import com.companyname.projectname.utils.ScreenshotUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;
public class ExtentReportManager {
    private static final Logger logger = LogManager.getLogger(ExtentReportManager.class);
    private static ExtentReports extent;
    private static String reportFilePath = ConfigReader.getProperty("report.dir") + File.separator + "ExtentReports_" + getCurrentDateTime() + ".html";
    private static ExtentHtmlReporter htmlReporter;

    private ExtentReportManager() {
        // Private constructor to prevent instantiation
    }

    public static synchronized ExtentReports getInstance() {
        if (extent == null) {
            startReport();
        }
        return extent;
    }

    public static void startReport() {
        extent = new ExtentReports();
        htmlReporter = new ExtentHtmlReporter(reportFilePath);
        ExtentHtmlReporterConfiguration config = htmlReporter.config();
        config.setDocumentTitle(ConfigReader.getProperty("report.title"));
        config.setReportName(ConfigReader.getProperty("report.name"));
        config.setTheme(Theme.DARK);
        config.setEncoding("utf-8");
        config.setTimeZone(TimeZone.getTimeZone("GMT"));
        config.setChartVisibilityOnOpen(true);
        extent.attachReporter(htmlReporter);
        extent.setSystemInfo("OS", System.getProperty("os.name"));
        extent.setSystemInfo("Browser", ConfigReader.getProperty("browser"));
        extent.setSystemInfo("Environment", ConfigReader.getProperty("environment"));
        logger.info("ExtentReports initialized at: {}", reportFilePath);
    }

    public static void flushReport() {
        if (extent != null) {
            extent.flush();
            logger.info("ExtentReports flushed and saved at: {}", reportFilePath);
        }
    }

    private static String getCurrentDateTime() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd_HHmmss");
        return sdf.format(new Date());
    }
}
