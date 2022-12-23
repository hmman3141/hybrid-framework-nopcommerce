package reportConfig;

import java.util.Objects;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.Status;

import commons.BaseTest;

import static reportConfig.ExtentTestManager.getTest;

public class TestListener extends BaseTest implements ITestListener {
	private static String getTestMethodName(ITestResult iTestResult) {
		return iTestResult.getMethod().getConstructorOrMethod().getName();
	}

	public static String getBase64Screenshot(WebDriver driver) {
		return "data:image/png;base64,"
				+ ((TakesScreenshot) Objects.requireNonNull(driver)).getScreenshotAs(OutputType.BASE64);
	}

	@Override
	public void onStart(ITestContext iTestContext) {
		log.info("I am in onStart method " + iTestContext.getName());
		iTestContext.setAttribute("WebDriver", this.getInstanceDriver());
	}

	@Override
	public void onFinish(ITestContext iTestContext) {
		log.info("I am in onFinish method " + iTestContext.getName());
		// Do tier down operations for ExtentReports reporting!
		ExtentManager.extentReports.flush();
	}

	@Override
	public void onTestStart(ITestResult iTestResult) {
		log.info(getTestMethodName(iTestResult) + " test is starting.");
		ExtentTestManager.startTest(iTestResult.getMethod().getMethodName(), "");
	}

	@Override
	public void onTestSuccess(ITestResult iTestResult) {
		log.info(getTestMethodName(iTestResult) + " test is succeed.");
		getTest().log(Status.PASS, "Test passed");
	}

	@Override
	public void onTestFailure(ITestResult iTestResult) {
		log.info(getTestMethodName(iTestResult) + " test is failed.");
		getTest().log(Status.FAIL, "Test Failed");
//		// Get driver from BaseTest and assign to local webdriver variable.
//		Object testClass = iTestResult.getInstance();
//		WebDriver driver = ((BaseTest) testClass).getInstanceDriver();
//		// Take base64Screenshot screenshot for extent reports
//		String base64Screenshot = getBase64Screenshot(driver);
//		// ExtentReports log and screenshot operations for failed tests.
//		getTest().log(Status.FAIL, "Test Failed",
//				getTest().addScreenCaptureFromBase64String(base64Screenshot).getModel().getMedia().get(0));
	}

	@Override
	public void onTestSkipped(ITestResult iTestResult) {
		log.info(getTestMethodName(iTestResult) + " test is skipped.");
		getTest().log(Status.SKIP, "Test Skipped");
	}

	@Override
	public void onTestFailedButWithinSuccessPercentage(ITestResult iTestResult) {
		log.info("Test failed but it is in defined success ratio " + getTestMethodName(iTestResult));
	}
}
