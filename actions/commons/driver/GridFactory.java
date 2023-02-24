package commons.driver;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import commons.GlobalConstants;
import commons.browser.ChromeDriverManager;
import commons.browser.EdgeDriverManager;
import commons.browser.FirefoxDriverManager;
import commons.browser.HeadlessChromeDriverManager;
import commons.browser.HeadlessFirefoxDriverManager;

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
		DesiredCapabilities capabilities = null;
		Platform platform = null;
		WebDriver driver = null;

		if (osName.contains("window")) {
			platform = Platform.WINDOWS;
		} else {
			platform = Platform.MAC;
		}

		switch (browserName) {
		case "firefox":
			capabilities = new FirefoxDriverManager().getGridDesiredCapabilities(platform);
			break;
		case "headless_firefox":
			capabilities = new HeadlessFirefoxDriverManager().getGridDesiredCapabilities(platform);
			break;
		case "chrome":
			capabilities = new ChromeDriverManager().getGridDesiredCapabilities(platform);
			break;
		case "headless_chrome":
			capabilities = new HeadlessChromeDriverManager().getGridDesiredCapabilities(platform);
			break;
		case "edge":
			capabilities = new EdgeDriverManager().getGridDesiredCapabilities(platform);
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
