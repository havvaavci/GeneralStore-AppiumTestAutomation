package com.havva.generalstore.listeners;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.havva.generalstore.utils.ExtentReportManager;
import com.havva.generalstore.utils.ReusableMethods;
import org.testng.*;

import java.io.IOException;

public class TestListener implements ITestListener {

    ExtentReports extent = ExtentReportManager.getExtentReports();
    ExtentTest test;

    @Override
    public void onTestStart(ITestResult result) {
        test = extent.createTest(result.getMethod().getMethodName());
        ExtentReportManager.setTest(test);
        test.info("Test started: " + result.getMethod().getMethodName());
    }



    @Override
    public void onTestSuccess(ITestResult result) {
        String testName = result.getMethod().getMethodName();
        test.pass("🟢 Test Passed → " + testName);
    }


    @Override
    public void onTestFailure(ITestResult result) {
        String testName = result.getMethod().getMethodName();

        // Fail log
        test.fail("🔴 Test Failed → " + testName);
        test.fail("Error: " + result.getThrowable());

        // Screenshot
        String screenshotPath = ReusableMethods.takeScreenshot(testName);

        try {
            test.addScreenCaptureFromPath(screenshotPath);
        } catch (IOException e) {
            test.warning("Screenshot could not be attached to report.");
            e.printStackTrace();
        }
    }


    @Override
    public void onFinish(ITestContext context) {
        test.info("📄 Test execution finished. Flushing report...");
        extent.flush();
    }

}
