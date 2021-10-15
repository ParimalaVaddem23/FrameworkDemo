package com.ecommerce.qa.listners;

import com.aventstack.extentreports.*;
import com.ecommerce.qa.intialize.BrowserFactory;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.ChartLocation;
import com.aventstack.extentreports.reporter.configuration.Theme;
import org.testng.ITestResult;

public class ExtentReportListner extends BrowserFactory implements ITestListener {
    private static final String OUTPUT_FOLDER = "./build/";
    private static final String FILE_NAME = "TestExecutionReport.html";
    static ExtentReports extentReports = ExtentReport_Init();
    public static ThreadLocal<ExtentTest> test = new ThreadLocal<>();

    private static ExtentReports ExtentReport_Init(){
        ExtentHtmlReporter htmlReporter;

        Path path = Paths.get(OUTPUT_FOLDER);
        if(!Files.exists(path)) {
            try {
                Files.createDirectory(path);
            } catch (Exception e){
                System.out.println(e.getMessage());
            }
        }
        htmlReporter = new ExtentHtmlReporter(OUTPUT_FOLDER+FILE_NAME);
        htmlReporter.config().setDocumentTitle("Automation Test Results");
        htmlReporter.config().setTestViewChartLocation(ChartLocation.TOP);
        htmlReporter.config().setTheme(Theme.STANDARD);
        extentReports = new ExtentReports();
        extentReports.attachReporter(htmlReporter);
        extentReports.setReportUsesManualConfiguration(true);
        return extentReports;
    }

    @Override
    public void onTestStart(ITestResult result) {
        String methodName = result.getMethod().getMethodName();
        String qualifiedName = result.getMethod().getQualifiedName();
        int last = qualifiedName.lastIndexOf(".");
        int mid = qualifiedName.substring(0,last).lastIndexOf(".");
        String className = qualifiedName.substring(mid+1,last);
        System.out.println(methodName+" started ");
        ExtentTest extentTest = extentReports.createTest(result.getMethod().getMethodName(),result.getMethod().getDescription());
        extentTest.assignCategory(result.getTestContext().getSuite().getName());
        extentTest.assignCategory(className);
        test.set(extentTest);
        test.get().getModel().setStartTime(getTime(result.getEndMillis()));
    }

    private Date getTime(long endMillis) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(endMillis);
        return calendar.getTime();
    }


    @Override
    public void onTestSuccess(ITestResult result) {
        System.out.println(result.getMethod().getMethodName()+" Test method is passed ");
        test.get().pass("passed");
        test.get().getModel().setStartTime(getTime(result.getEndMillis()));
    }

    @Override
    public void onTestFailure(ITestResult result) {
        System.out.println(result.getMethod().getMethodName()+" Test method is failed ");
        try {
            test.get().fail(result.getThrowable(), MediaEntityBuilder.createScreenCaptureFromPath(getScreenshot()).build());
        } catch (Exception e) {
            System.err.println(Arrays.toString(e.getStackTrace()));
        }
        test.get().getModel().setStartTime(getTime(result.getEndMillis()));
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        System.out.println(result.getMethod().getMethodName()+" Test method is skipped ");
        try {
            test.get().fail(result.getThrowable(), MediaEntityBuilder.createScreenCaptureFromPath(getScreenshot()).build());
        } catch (Exception e) {
            System.err.println(Arrays.toString(e.getStackTrace()));
        }
        test.get().getModel().setStartTime(getTime(result.getEndMillis()));
    }

    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
        System.out.println("onTestFailedButWithinSuccessPercentage"+result.getMethod().getSuccessPercentage());
    }

    public synchronized void onStart(ITestContext context){
        System.out.println("Test Execution started");
    }

    public synchronized void onFinish(ITestContext context){
        extentReports.flush();
        System.out.println("Execution finished");
    }
}
