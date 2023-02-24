package commons.browser;

import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.DesiredCapabilities;

import io.github.bonigarcia.wdm.WebDriverManager;

public class HeadlessFirefoxDriverManager implements BrowserFactory {
	@Override
	public WebDriver getBrowserDriver() {
		FirefoxOptions headlessFirefoxOption = new FirefoxOptions();
		headlessFirefoxOption.addArguments("-headless");
		headlessFirefoxOption.addArguments("window-size=1920x1980");
		WebDriverManager.firefoxdriver().setup();
		return new FirefoxDriver(headlessFirefoxOption);
	}

	@Override
	public DesiredCapabilities getGridDesiredCapabilities(Platform platform) {
		DesiredCapabilities capabilities = new DesiredCapabilities();
		capabilities = DesiredCapabilities.firefox();
		capabilities.setBrowserName("firefox");
		capabilities.setPlatform(platform);

		FirefoxOptions headlessFirefoxOptions = new FirefoxOptions();
		headlessFirefoxOptions.addArguments("-headless");
		headlessFirefoxOptions.addArguments("window-size=1920x1980");
		headlessFirefoxOptions.merge(capabilities);
		return capabilities;
	}

}
