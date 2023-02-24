package commons.browser;

import java.io.File;

import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.remote.DesiredCapabilities;

import commons.GlobalConstants;
import io.github.bonigarcia.wdm.WebDriverManager;

public class FirefoxDriverManager implements BrowserFactory {

	@Override
	public WebDriver getBrowserDriver() {
		System.setProperty(FirefoxDriver.SystemProperty.DRIVER_USE_MARIONETTE, "true");
		System.setProperty(FirefoxDriver.SystemProperty.BROWSER_LOGFILE,
				String.format(GlobalConstants.PROJECT_PATH, "browserLogs", "firefoxLogs.txt"));

		FirefoxProfile firefoxProfile = new FirefoxProfile();
		File firefoxExtension = new File(
				String.format(GlobalConstants.PROJECT_PATH, "browserExtension", "to_google_translate-4.2.0.xpi"));
		firefoxProfile.addExtension(firefoxExtension);
		FirefoxOptions firefoxOption = new FirefoxOptions();
		firefoxOption.setProfile(firefoxProfile);
		WebDriverManager.firefoxdriver().setup();
		return new FirefoxDriver(firefoxOption);
	}

	@Override
	public DesiredCapabilities getGridDesiredCapabilities(Platform platform) {
		System.setProperty(FirefoxDriver.SystemProperty.DRIVER_USE_MARIONETTE, "true");
		System.setProperty(FirefoxDriver.SystemProperty.BROWSER_LOGFILE,
				String.format(GlobalConstants.PROJECT_PATH, "browserLogs", "firefoxLogs.txt"));

		DesiredCapabilities capabilities = new DesiredCapabilities();
		capabilities = DesiredCapabilities.firefox();
		capabilities.setBrowserName("firefox");
		capabilities.setPlatform(platform);

		FirefoxProfile firefoxProfile = new FirefoxProfile();
		firefoxProfile.addExtension(new File(
				String.format(GlobalConstants.PROJECT_PATH, "browserExtension", "to_google_translate-4.2.0.xpi")));
		FirefoxOptions firefoxOptions = new FirefoxOptions();
		firefoxOptions.setProfile(firefoxProfile);
		firefoxOptions.merge(capabilities);
		return capabilities;
	}

}
