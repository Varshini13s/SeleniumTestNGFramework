package com.automation.listeners;

import com.automation.utils.DriverManager;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

public class ExtentReportListener implements ITestListener {
    ExtentReports extentReports;
    ExtentTest extentTest;

    @Override
    public void onStart(ITestContext context) {
        ExtentSparkReporter sparkReporter = new ExtentSparkReporter("report/extent-report.html");
        sparkReporter.config().setReportName("Automation Execution Report");

        extentReports = new ExtentReports();
        extentReports.attachReporter(sparkReporter);
    }

    @Override
    public void onTestStart(ITestResult result) {
        extentTest = extentReports.createTest(result.getMethod().getMethodName());
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        extentTest.log(Status.PASS, "Test Passed");
        extentTest.log(Status.INFO, "Screenshot",
                MediaEntityBuilder.createScreenCaptureFromBase64String(DriverManager.takeScreenshotAsBase64()).build());
        extentTest.addScreenCaptureFromPath(DriverManager.takeScreenshotAsFile());
    }

    @Override
    public void onTestFailure(ITestResult result) {
        extentTest.log(Status.PASS, "Test Failed");
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        extentTest.log(Status.PASS, "Test Skipped");
    }

    @Override
    public void onFinish(ITestContext context){
        extentReports.flush();
    }

}
