package com.havva.generalstore.utils;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

import java.io.File;

public class ExtentReportManager {

    private static ExtentReports extent;
    private static ExtentTest test;

    public static ExtentReports getExtentReports() {

        if (extent == null) {

            // 1) reports
            String reportFolder = System.getProperty("user.dir") + "/reports";
            File folder = new File(reportFolder);
            if (!folder.exists()) {
                folder.mkdirs(); // klasör yoksa oluştur
            }

            // 2) report path
            String reportPath = reportFolder + "/GeneralStoreReport.html";

            // 3) reporter settings
            ExtentSparkReporter reporter = new ExtentSparkReporter(reportPath);
            reporter.config().setReportName("General Store Appium Test Automation Report");
            reporter.config().setDocumentTitle("Automation Report");

            // 4) extent settings
            extent = new ExtentReports();
            extent.attachReporter(reporter);
            extent.setSystemInfo("Tester", "Havva");
            extent.setSystemInfo("Framework", "Appium + TestNG");
        }

        return extent;
    }
    public static void setTest(ExtentTest extentTest) {
        test = extentTest;
    }

    public static ExtentTest getTest() {
        return test;
    }
}
