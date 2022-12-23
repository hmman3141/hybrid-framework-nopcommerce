package commons;

import static reportConfig.ExtentTestManager.getTest;

import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.Reporter;

import com.aventstack.extentreports.Status;

import io.github.bonigarcia.wdm.WebDriverManager;
import static reportConfig.TestListener.getBase64Screenshot;

public class BaseTest {
	private WebDriver driver;
	protected final Log log;

	private String cocCocPath = "C:\\Program Files\\CocCoc\\Browser\\Application\\browser.exe";
	private String cocCocDriverVersion = "106.0.5249.61";
	private String firefoxDriverVersion = "0.31.0";

	public BaseTest() {
		log = LogFactory.getLog(getClass());
	}

	public WebDriver getInstanceDriver() {
		return this.driver;
	}

	protected WebDriver getBrowserDriver(String browserName) {
		switch (browserName) {
		case "firefox":
			WebDriverManager.firefoxdriver().driverVersion(firefoxDriverVersion).setup();
			driver = new FirefoxDriver();
			break;
		case "chrome":
			WebDriverManager.chromedriver().setup();
			driver = new ChromeDriver();
			break;
		case "coccoc":
			WebDriverManager.chromedriver().driverVersion(cocCocDriverVersion).setup();
			ChromeOptions option = new ChromeOptions();
			option.setBinary(cocCocPath);
			driver = new ChromeDriver(option);
			break;
		case "edge":
			WebDriverManager.edgedriver().setup();
			driver = new EdgeDriver();
			break;
		default:
			throw new RuntimeException("Invalid browser name");
		}

		driver.manage().timeouts().implicitlyWait(GlobalConstants.LONG_TIMEOUT, TimeUnit.SECONDS);
		return driver;
	}

	protected int getRandomNumber() {
		Random rand = new Random();
		return rand.nextInt();
	}

	private boolean checkTrue(boolean condition) {
		boolean pass = true;
		try {
			Assert.assertTrue(condition);
			System.out.println(" -------------------------- PASSED -------------------------- ");
		} catch (Throwable e) {
			pass = false;
			System.out.println(" -------------------------- FAILED -------------------------- ");
			FailureVerification.getFailures().addFailureForTest(Reporter.getCurrentTestResult(), e);
			Reporter.getCurrentTestResult().setThrowable(e);
			Fail();
		}
		return pass;
	}

	protected boolean verifyTrue(boolean condition) {
		return checkTrue(condition);
	}

	private boolean checkFailed(boolean condition) {
		boolean pass = true;
		try {
			Assert.assertFalse(condition);
			System.out.println(" -------------------------- PASSED -------------------------- ");
		} catch (Throwable e) {
			pass = false;
			System.out.println(" -------------------------- FAILED -------------------------- ");
			FailureVerification.getFailures().addFailureForTest(Reporter.getCurrentTestResult(), e);
			Reporter.getCurrentTestResult().setThrowable(e);
			Fail();
		}
		return pass;
	}

	protected boolean verifyFalse(boolean condition) {
		return checkFailed(condition);
	}

	private boolean checkEquals(Object actual, Object expected) {
		boolean pass = true;
		try {
			Assert.assertEquals(actual, expected);
			System.out.println(" -------------------------- PASSED -------------------------- ");
		} catch (Throwable e) {
			pass = false;
			System.out.println(" -------------------------- PASSED -------------------------- ");
			FailureVerification.getFailures().addFailureForTest(Reporter.getCurrentTestResult(), e);
			Reporter.getCurrentTestResult().setThrowable(e);
			Fail();
		}
		return pass;
	}

	protected boolean verifyEquals(Object actual, Object expected) {
		return checkEquals(actual, expected);
	}

	protected void Info(String message) {
		log.info(message);
		getTest().log(Status.INFO, message);
	}

	private void Fail() {
		String base64Screenshot = getBase64Screenshot(driver);
		getTest().log(Status.FAIL, "Step Failed",
				getTest().addScreenCaptureFromBase64String(base64Screenshot).getModel().getMedia().get(0));
	}
}
