package commons.driver;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import commons.GlobalConstants;
import commons.browser.ChromeDriverManager;

public class SaucelabFactory {
	private String buildId;
	private String email;
	private String id;

	public SaucelabFactory(String buildId, String email, String id) {
		this.buildId = buildId;
		this.email = email;
		this.id = id;
	}

	public WebDriver getBrowserDriver(String browserName, String url) {
		DesiredCapabilities capabilities = null;
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
			capabilities = new ChromeDriverManager().getSaucelabDesiredCapabilities(buildId);
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
