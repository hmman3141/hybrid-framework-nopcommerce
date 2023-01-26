package reportConfig;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import commons.BaseLog;
import commons.BaseTest;

public class TestListener extends BaseTest implements ITestListener {
	public static BaseLog log4J;
	
	private static String getTestMethodName(ITestResult iTestResult) {
		return iTestResult.getMethod().getConstructorOrMethod().getName();
	}
	
	public static void startTest(String testName, String desc) {
		ExtentTestManager.startTest(testName, desc);
	}
	
	public static void endTest() {
		ExtentManager.extentReports.flush();
	}

	@Override
	public void onStart(ITestContext iTestContext) {
		log4J = new BaseLog();
		iTestContext.setAttribute("WebDriver", this.getInstanceDriver());
	}

	@Override
	public void onFinish(ITestContext iTestContext) {
		endTest();
	}

	@Override
	public void onTestStart(ITestResult iTestResult) {
		startTest(iTestResult.getMethod().getMethodName(), "");
		log4J.Info(getTestMethodName(iTestResult) + " test is starting.");
	}

	@Override
	public void onTestSuccess(ITestResult iTestResult) {
		log4J.Pass("TEST SUCCEEDED");
	}

	@Override
	public void onTestFailure(ITestResult iTestResult) {
		log4J.Fail("TEST FAILED");
	}

	@Override
	public void onTestSkipped(ITestResult iTestResult) {
		log4J.Skip("TEST SKIPPED");
	}

	@Override
	public void onTestFailedButWithinSuccessPercentage(ITestResult iTestResult) {
	}
}
