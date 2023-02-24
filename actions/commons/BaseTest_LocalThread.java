package commons;

import static reportConfig.TestListener.log4J;

import java.io.File;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.testng.Assert;
import org.testng.Reporter;

import io.github.bonigarcia.wdm.WebDriverManager;;

public class BaseTest_LocalThread {
	protected static ThreadLocal<WebDriver> driver = new ThreadLocal<WebDriver>();

	private String cocCocPath = "C:\\Program Files\\CocCoc\\Browser\\Application\\browser.exe";
	private String cocCocDriverVersion = "106.0.5249.61";
	private String firefoxDriverVersion = "0.31.0";

	public static String getPath(String... variables) {
		return String.join(File.separator, variables);
	}

	protected void getBrowserDriver(String browserName, String url) {
		switch (browserName) {
		case "firefox":
			System.setProperty(FirefoxDriver.SystemProperty.DRIVER_USE_MARIONETTE, "true");
			System.setProperty(FirefoxDriver.SystemProperty.BROWSER_LOGFILE,
					getPath(GlobalConstants.PROJECT_PATH, "browserLogs", "firefoxLogs.txt"));
			FirefoxProfile firefoxProfile = new FirefoxProfile();
			File firefoxExtension = new File(
					getPath(GlobalConstants.PROJECT_PATH, "browserExtension", "to_google_translate-4.2.0.xpi"));
			firefoxProfile.addExtension(firefoxExtension);
			FirefoxOptions firefoxOption = new FirefoxOptions();
			firefoxOption.setProfile(firefoxProfile);
			WebDriverManager.firefoxdriver().driverVersion(firefoxDriverVersion).setup();
			driver.set(new FirefoxDriver(firefoxOption));
			break;
		case "headless_firefox":
			FirefoxOptions headlessFirefoxOption = new FirefoxOptions();
			headlessFirefoxOption.addArguments("-headless");
			headlessFirefoxOption.addArguments("window-size=1920x1980");
			WebDriverManager.firefoxdriver().driverVersion(firefoxDriverVersion).setup();
			driver.set(new FirefoxDriver(headlessFirefoxOption));
			break;
		case "chrome":
			System.setProperty("webdriver.chrome.args", "--disable-logging");
			System.setProperty("webdriver.chrome.logfile",
					getPath(GlobalConstants.PROJECT_PATH, "browserLogs", "chromeLogs.txt"));
			System.setProperty("webdriver.chrome.verboseLogging", "true");
			File extensionFile = new File(
					getPath(GlobalConstants.PROJECT_PATH, "browserExtension", "extension_2_0_12_0.crx"));
			ChromeOptions chromeOption = new ChromeOptions();
			chromeOption.addExtensions(extensionFile);
			WebDriverManager.chromedriver().setup();
			driver.set(new ChromeDriver(chromeOption));
			break;
		case "headless_chrome":
			ChromeOptions headlessChromeOption = new ChromeOptions();
			headlessChromeOption.addArguments("-headless");
			headlessChromeOption.addArguments("window-size=1920x1080");
			WebDriverManager.chromedriver().setup();
			driver.set(new ChromeDriver(headlessChromeOption));
			break;
		case "coccoc":
			WebDriverManager.chromedriver().driverVersion(cocCocDriverVersion).setup();
			ChromeOptions option = new ChromeOptions();
			option.setBinary(cocCocPath);
			driver.set(new ChromeDriver(option));
			break;
		case "edge":
			WebDriverManager.edgedriver().setup();
			driver.set(new EdgeDriver());
			break;
		default:
			throw new RuntimeException("Invalid browser name");
		}

		driver.get().manage().timeouts().implicitlyWait(GlobalConstants.LONG_TIMEOUT, TimeUnit.SECONDS);
		driver.get().get(url);
	}

	protected int getRandomNumber() {
		Random rand = new Random();
		return rand.nextInt();
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
			log4J.Fail(driver.get());
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
			log4J.Fail(driver.get());
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
			log4J.Fail(driver.get());
		}
		return pass;
	}

	protected boolean verifyEquals(Object actual, Object expected) {
		return checkEquals(actual, expected);
	}
}
