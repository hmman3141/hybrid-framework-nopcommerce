package commons.driver;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import commons.GlobalConstants;

public class GridFactory {
	private String osName;
	private String ipAddress;
	private String portNumber;

	public GridFactory(String osName, String ipAddress, String portNumber) {
		this.osName = osName;
		this.ipAddress = ipAddress;
		this.portNumber = portNumber;
	}

	public WebDriver getBrowserDriver(String browserName, String url) {
		DesiredCapabilities capabilities = new DesiredCapabilities();
		Platform platform = null;
		WebDriver driver = null;

		if (osName.contains("window")) {
			platform = Platform.WINDOWS;
		} else {
			platform = Platform.MAC;
		}

		switch (browserName) {
		case "firefox":
			System.setProperty(FirefoxDriver.SystemProperty.DRIVER_USE_MARIONETTE, "true");
			System.setProperty(FirefoxDriver.SystemProperty.BROWSER_LOGFILE,
					String.format(GlobalConstants.PROJECT_PATH, "browserLogs", "firefoxLogs.txt"));

			capabilities = DesiredCapabilities.firefox();
			capabilities.setBrowserName("firefox");
			capabilities.setPlatform(platform);

			FirefoxProfile firefoxProfile = new FirefoxProfile();
			firefoxProfile.addExtension(new File(
					String.format(GlobalConstants.PROJECT_PATH, "browserExtension", "to_google_translate-4.2.0.xpi")));
			FirefoxOptions firefoxOptions = new FirefoxOptions();
			firefoxOptions.setProfile(firefoxProfile);
			firefoxOptions.merge(capabilities);
			break;
		case "headless_firefox":
			capabilities = DesiredCapabilities.firefox();
			capabilities.setBrowserName("firefox");
			capabilities.setPlatform(platform);

			FirefoxOptions headlessFirefoxOptions = new FirefoxOptions();
			headlessFirefoxOptions.addArguments("-headless");
			headlessFirefoxOptions.addArguments("window-size=1920x1980");
			headlessFirefoxOptions.merge(capabilities);
			break;
		case "chrome":
			System.setProperty("webdriver.chrome.args", "--disable-logging");
			System.setProperty("webdriver.chrome.logfile",
					String.format(GlobalConstants.PROJECT_PATH, "browserLogs", "chromeLogs.txt"));
			System.setProperty("webdriver.chrome.verboseLogging", "true");

			capabilities = DesiredCapabilities.chrome();
			capabilities.setBrowserName("chrome");
			capabilities.setPlatform(platform);

			ChromeOptions chromeOption = new ChromeOptions();
			chromeOption.addExtensions(new File(
					String.format(GlobalConstants.PROJECT_PATH, "browserExtension", "extension_2_0_12_0.crx")));
			chromeOption.merge(capabilities);
			break;
		case "headless_chrome":
			capabilities = DesiredCapabilities.chrome();
			capabilities.setBrowserName("chrome");
			capabilities.setPlatform(platform);

			ChromeOptions headlessChromeOption = new ChromeOptions();
			headlessChromeOption.addArguments("-headless");
			headlessChromeOption.addArguments("window-size=1920x1080");
			headlessChromeOption.merge(capabilities);
			break;
		case "edge":
			capabilities = DesiredCapabilities.edge();
			capabilities.setBrowserName("edge");
			capabilities.setPlatform(platform);

			EdgeOptions edgeOptions = new EdgeOptions();
			edgeOptions.merge(capabilities);
			break;
		default:
			throw new RuntimeException("Invalid browser name");
		}

		try {
			driver = new RemoteWebDriver(new URL(String.format("http://%s:%s/wd/hub", ipAddress, portNumber)),
					capabilities);
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}

		driver.manage().timeouts().implicitlyWait(GlobalConstants.LONG_TIMEOUT, TimeUnit.SECONDS);
		driver.get(url);
		return driver;
	}
}
