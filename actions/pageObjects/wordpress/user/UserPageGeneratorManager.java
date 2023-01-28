package pageObjects.wordpress.user;

import org.openqa.selenium.WebDriver;

import pageUIs.wordpress.admin.BasePageUI;

public class UserPageGeneratorManager {
	public static UserDashboardPageObject openUserURL(WebDriver driver) {
		driver.get(BasePageUI.USER_URL);
		return new UserDashboardPageObject(driver);
	}
}
