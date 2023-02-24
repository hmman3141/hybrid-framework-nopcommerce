package commons.browser;

import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;

import io.github.bonigarcia.wdm.WebDriverManager;

public class EdgeDriverManager implements BrowserFactory {
	
	@Override
	public WebDriver getBrowserDriver() {
		WebDriverManager.edgedriver().setup();
		return new EdgeDriver();
	}

	@Override
	public DesiredCapabilities getGridDesiredCapabilities(Platform platform) {
		DesiredCapabilities capabilities = new DesiredCapabilities();
		capabilities = DesiredCapabilities.edge();
		capabilities.setBrowserName("edge");
		capabilities.setPlatform(platform);

		EdgeOptions edgeOptions = new EdgeOptions();
		edgeOptions.merge(capabilities);
		return null;
	}

}
