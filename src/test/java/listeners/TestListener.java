package listeners;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;

import factory.DriverFactory;

import org.testng.ITestListener;
import org.testng.ITestContext;
import org.testng.ITestResult;
import reporting.ExtentManager;
import utils.ScreenshotUtil;

public class TestListener implements ITestListener {

    private static ExtentReports extent;
    private static ThreadLocal<ExtentTest> test = new ThreadLocal<>();

    @Override
    public void onStart(ITestContext context) {
        extent = ExtentManager.getExtent();
    }

    @Override
    public void onTestStart(ITestResult result) {
        ExtentTest extentTest = extent.createTest(result.getMethod().getMethodName());
        test.set(extentTest);
    }

    @Override
    public void onTestFailure(ITestResult result) {
        ExtentTest currentTest = test.get();
        currentTest.fail("Test Failed");

        // Take screenshot only if WebDriver exists
        if (DriverFactory.getDriver() != null) {
            String screenshotPath =
                    ScreenshotUtil.takeScreenshot(result.getMethod().getMethodName());

            if (screenshotPath != null) {
                currentTest.addScreenCaptureFromPath(screenshotPath);
            }
        }
    }


    @Override
    public void onTestSuccess(ITestResult result) {
        test.get().pass("Test Passed");
    }

    @Override
    public void onFinish(ITestContext context) {
        if (extent != null) {
            extent.flush();
        }
    }
}
