package com.myproject.framework;

import lombok.extern.java.Log;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author Roman Onyshkiv
 */
@Log
public class TestListener implements ITestListener {

    @Override
    public void onTestStart(ITestResult result) {

    }

    @Override
    public void onTestSuccess(ITestResult result) {
        try {
            log("*** Result = PASSED ***\n");
            log(result.getEndMillis(), " TEST -> " + result.getInstanceName() + "." + result.getName());
            log("\n");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onTestFailure(ITestResult result) {

    }

    @Override
    public void onTestSkipped(ITestResult result) {

    }

    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult result) {

    }

    @Override
    public void onTestFailedWithTimeout(ITestResult result) {

    }

    @Override
    public void onStart(ITestContext context) {
        try {
            log.info("\nSuite Start Date: " +
                    new SimpleDateFormat("MM.dd.yyyy.HH.mm.ss")
                            .format(new Date()) + ".log");
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Override
    public void onFinish(ITestContext context) {
        try {
            log("\nTotal Passed = " + getPassedTests().size() +
                    "\nTotal Failed = " + getFailedTests().size() +
                    "\nTotal Skipped = " + getSkippedTests().size() + "\n");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static String logFile = null;

    public void log(long date, String dataLine) throws Exception {
        System.out.format("%s%n", String.valueOf(new Date(date)), dataLine);
        if (logFile != null) {
            writeLogFile(logFile, dataLine);
        }
    }

    public void log(String dataLine) throws Exception {
        System.out.format("%s%n", dataLine);
        if (logFile != null) {
            writeLogFile(logFile, dataLine);
        }
    }
}
