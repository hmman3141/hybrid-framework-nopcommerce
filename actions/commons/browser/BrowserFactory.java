package commons.browser;

import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;

public interface BrowserFactory {
	WebDriver getBrowserDriver();
	DesiredCapabilities getGridDesiredCapabilities(Platform platform);
}
