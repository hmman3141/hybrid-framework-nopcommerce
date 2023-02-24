package commons.driver;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import commons.GlobalConstants;

public class SaucelabFactory {
	private String buildId;
	private String email;
	private String id;

	public SaucelabFactory(String buildId, String email, String id) {
		this.buildId = buildId;
		this.email = email;
		this.id = id;
	}

	public WebDriver getBrowserDriver(String browserName,String url) {
		DesiredCapabilities capabilities = new DesiredCapabilities();
		WebDriver driver = null;
		URL serverUrl = null;

		try {
			serverUrl = new URL(
					String.format("https://oauth-%s:%s@ondemand.eu-central-1.saucelabs.com:443/wd/hub", email, id));
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}

		switch (browserName) {
		case "chrome":
			System.setProperty("webdriver.chrome.args", "--disable-logging");
			System.setProperty("webdriver.chrome.logfile",
					String.format(GlobalConstants.PROJECT_PATH, "browserLogs", "chromeLogs.txt"));
			System.setProperty("webdriver.chrome.verboseLogging", "true");

			capabilities = DesiredCapabilities.chrome();
			capabilities.setCapability("platformName", "Windows 10");
			capabilities.setCapability("browserVersion", "latest");
			Map<String, Object> sauceOptions = new HashMap<>();
			sauceOptions.put("build", buildId);
			sauceOptions.put("name", "Chrome test");
			capabilities.setCapability("sauce:options", sauceOptions);
			break;
		default:
			throw new RuntimeException("Invalid browser name");
		}

		driver = new RemoteWebDriver(serverUrl, capabilities);

		driver.manage().timeouts().implicitlyWait(GlobalConstants.LONG_TIMEOUT, TimeUnit.SECONDS);
		driver.get(url);
		return driver;
	}
}
