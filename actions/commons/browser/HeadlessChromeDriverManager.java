package commons.browser;

import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;

import io.github.bonigarcia.wdm.WebDriverManager;

public class HeadlessChromeDriverManager implements BrowserFactory {

	@Override
	public WebDriver getBrowserDriver() {
		ChromeOptions headlessChromeOption = new ChromeOptions();
		headlessChromeOption.addArguments("-headless");
		headlessChromeOption.addArguments("window-size=1920x1080");
		WebDriverManager.chromedriver().setup();
		return new ChromeDriver(headlessChromeOption);
	}

	@Override
	public DesiredCapabilities getGridDesiredCapabilities(Platform platform) {
		DesiredCapabilities capabilities = new DesiredCapabilities();
		capabilities = DesiredCapabilities.chrome();
		capabilities.setBrowserName("chrome");
		capabilities.setPlatform(platform);

		ChromeOptions headlessChromeOption = new ChromeOptions();
		headlessChromeOption.addArguments("-headless");
		headlessChromeOption.addArguments("window-size=1920x1080");
		headlessChromeOption.merge(capabilities);
		return capabilities;
	}

}
