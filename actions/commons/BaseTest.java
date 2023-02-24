package commons;

import static reportConfig.TestListener.log4J;

import java.io.File;
import java.io.IOException;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.Reporter;

import commons.browser.ChromeDriverManager;
import commons.browser.CoccocDriverManager;
import commons.browser.EdgeDriverManager;
import commons.browser.FirefoxDriverManager;
import commons.browser.HeadlessChromeDriverManager;
import commons.browser.HeadlessFirefoxDriverManager;;

public class BaseTest {
	private WebDriver driver;

	public WebDriver getInstanceDriver() {
		return this.driver;
	}

	public static String getPath(String... variables) {
		return String.join(File.separator, variables);
	}

	protected WebDriver getBrowserDriver(String browserName) {
		switch (browserName) {
		case "firefox":
			driver = new FirefoxDriverManager().getBrowserDriver();
			break;
		case "headless_firefox":
			driver = new HeadlessFirefoxDriverManager().getBrowserDriver();
			break;
		case "chrome":
			driver = new ChromeDriverManager().getBrowserDriver();
			break;
		case "headless_chrome":
			driver = new HeadlessChromeDriverManager().getBrowserDriver();
			break;
		case "coccoc":
			driver = new CoccocDriverManager().getBrowserDriver();
			break;
		case "edge":
			driver = new EdgeDriverManager().getBrowserDriver();
			break;
		default:
			throw new RuntimeException("Invalid browser name");
		}

		driver.manage().timeouts().implicitlyWait(GlobalConstants.LONG_TIMEOUT, TimeUnit.SECONDS);
		return driver;
	}

	protected WebDriver getBrowserDriver(String browserName, String url) {
		switch (browserName) {
		case "firefox":
			driver = new FirefoxDriverManager().getBrowserDriver();
			break;
		case "headless_firefox":
			driver = new HeadlessFirefoxDriverManager().getBrowserDriver();
			break;
		case "chrome":
			driver = new ChromeDriverManager().getBrowserDriver();
			break;
		case "headless_chrome":
			driver = new HeadlessChromeDriverManager().getBrowserDriver();
			break;
		case "coccoc":
			driver = new CoccocDriverManager().getBrowserDriver();
			break;
		case "edge":
			driver = new EdgeDriverManager().getBrowserDriver();
			break;
		default:
			throw new RuntimeException("Invalid browser name");
		}

		driver.manage().timeouts().implicitlyWait(GlobalConstants.LONG_TIMEOUT, TimeUnit.SECONDS);
		driver.get(url);
		return driver;
	}


	protected int getRandomNumber() {
		Random rand = new Random();
		return rand.nextInt();
	}

	protected void closeBrowserDriver() {
		String cmd = null;
		try {
			String osName = GlobalConstants.OS_NAME;
			log4J.Info("OS name = " + osName);

			String driverInstanceName = driver.toString().toLowerCase();
			log4J.Info("Driver instance name = " + driverInstanceName);

			String browserDriverName = null;

			if (driverInstanceName.contains("chrome")) {
				browserDriverName = "chromedriver";
			} else if (driverInstanceName.contains("internetexplorer")) {
				browserDriverName = "IEDriverServer";
			} else if (driverInstanceName.contains("firefox")) {
				browserDriverName = "geckodriver";
			} else if (driverInstanceName.contains("edge")) {
				browserDriverName = "msedgedriver";
			} else if (driverInstanceName.contains("opera")) {
				browserDriverName = "operadriver";
			} else {
				browserDriverName = "safaridriver";
			}

			if (osName.contains("window")) {
				cmd = "taskkill /F /FI \"IMAGENAME eq " + browserDriverName + "*\"";
			} else {
				cmd = "pkill " + browserDriverName;
			}

			if (driver != null) {
				driver.manage().deleteAllCookies();
				driver.quit();
			}
		} catch (Exception e) {
			log4J.Info(e.getMessage());
		} finally {
			try {
				Process process = Runtime.getRuntime().exec(cmd);
				process.waitFor();
			} catch (IOException e) {
				e.printStackTrace();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	private boolean checkTrue(boolean condition) {
		boolean pass = true;
		try {
			Assert.assertTrue(condition);
			log4J.Pass();
		} catch (Throwable e) {
			pass = false;
			FailureVerification.getFailures().addFailureForTest(Reporter.getCurrentTestResult(), e);
			Reporter.getCurrentTestResult().setThrowable(e);
			log4J.Fail(driver);
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
			log4J.Pass();
		} catch (Throwable e) {
			pass = false;
			FailureVerification.getFailures().addFailureForTest(Reporter.getCurrentTestResult(), e);
			Reporter.getCurrentTestResult().setThrowable(e);
			log4J.Fail(driver);
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
			log4J.Pass();
		} catch (Throwable e) {
			pass = false;
			FailureVerification.getFailures().addFailureForTest(Reporter.getCurrentTestResult(), e);
			Reporter.getCurrentTestResult().setThrowable(e);
			log4J.Fail(driver);
		}
		return pass;
	}

	protected boolean verifyEquals(Object actual, Object expected) {
		return checkEquals(actual, expected);
	}
}
