package commons.browser;

import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;

import io.github.bonigarcia.wdm.WebDriverManager;

public class CoccocDriverManager implements BrowserFactory {
	private String cocCocPath = "C:\\Program Files\\CocCoc\\Browser\\Application\\browser.exe";
	private String cocCocDriverVersion = "106.0.5249.61";
	
	@Override
	public WebDriver getBrowserDriver() {
		WebDriverManager.chromedriver().driverVersion(cocCocDriverVersion).setup();
		ChromeOptions cocCocOption = new ChromeOptions();
		cocCocOption.setBinary(cocCocPath);
		return new ChromeDriver(cocCocOption);
	}

	@Override
	public DesiredCapabilities getGridDesiredCapabilities(Platform platform) {
		return null;
	}

}
