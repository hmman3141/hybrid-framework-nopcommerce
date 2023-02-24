package commons.browser;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;

import commons.GlobalConstants;
import io.github.bonigarcia.wdm.WebDriverManager;

public class ChromeDriverManager implements BrowserFactory {

	@Override
	public WebDriver getBrowserDriver() {
		System.setProperty("webdriver.chrome.args", "--disable-logging");
		System.setProperty("webdriver.chrome.logfile",
				String.format(GlobalConstants.PROJECT_PATH, "browserLogs", "chromeLogs.txt"));

		System.setProperty("webdriver.chrome.verboseLogging", "true");
		File extensionFile = new File(
				String.format(GlobalConstants.PROJECT_PATH, "browserExtension", "extension_2_0_12_0.crx"));
		ChromeOptions chromeOption = new ChromeOptions();
		chromeOption.addExtensions(extensionFile);
		WebDriverManager.chromedriver().setup();
		return new ChromeDriver(chromeOption);
	}

	@Override
	public DesiredCapabilities getGridDesiredCapabilities(Platform platform) {
		System.setProperty("webdriver.chrome.args", "--disable-logging");
		System.setProperty("webdriver.chrome.logfile",
				String.format(GlobalConstants.PROJECT_PATH, "browserLogs", "chromeLogs.txt"));
		System.setProperty("webdriver.chrome.verboseLogging", "true");

		DesiredCapabilities capabilities = new DesiredCapabilities();
		capabilities = DesiredCapabilities.chrome();
		capabilities.setBrowserName("chrome");
		capabilities.setPlatform(platform);

		ChromeOptions chromeOption = new ChromeOptions();
		chromeOption.addExtensions(
				new File(String.format(GlobalConstants.PROJECT_PATH, "browserExtension", "extension_2_0_12_0.crx")));
		chromeOption.merge(capabilities);
		return capabilities;
	}

	public DesiredCapabilities getSaucelabDesiredCapabilities(String buildId) {
		System.setProperty("webdriver.chrome.args", "--disable-logging");
		System.setProperty("webdriver.chrome.logfile",
				String.format(GlobalConstants.PROJECT_PATH, "browserLogs", "chromeLogs.txt"));
		System.setProperty("webdriver.chrome.verboseLogging", "true");

		DesiredCapabilities capabilities = new DesiredCapabilities();
		capabilities = DesiredCapabilities.chrome();
		capabilities.setCapability("platformName", "Windows 10");
		capabilities.setCapability("browserVersion", "latest");
		Map<String, Object> sauceOptions = new HashMap<>();
		sauceOptions.put("build", buildId);
		sauceOptions.put("name", "Chrome test");
		capabilities.setCapability("sauce:options", sauceOptions);
		return capabilities;
	}
}
