package commons;

import static reportConfig.TestListener.log4J;

import java.io.IOException;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.Reporter;

import io.github.bonigarcia.wdm.WebDriverManager;;

public class BaseTest {
	private WebDriver driver;

	private String cocCocPath = "C:\\Program Files\\CocCoc\\Browser\\Application\\browser.exe";
	private String cocCocDriverVersion = "106.0.5249.61";
	private String firefoxDriverVersion = "0.31.0";

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

	protected WebDriver getBrowserDriver(String browserName, String url) {
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
