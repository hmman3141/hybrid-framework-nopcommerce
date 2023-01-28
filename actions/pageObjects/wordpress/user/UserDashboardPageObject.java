package pageObjects.wordpress.user;

import org.openqa.selenium.WebDriver;

import commons.GlobalConstants;
import commons.wordpress.BasePage_Wordpress;
import pageUIs.wordpress.user.UserDashboardPageUI;

public class UserDashboardPageObject extends BasePage_Wordpress {
	private WebDriver driver;

	public UserDashboardPageObject(WebDriver driver) {
		this.driver = driver;
	}

	public boolean isTitleDisplayedByPostTitle(String title) {
		overrideGlobalImplicitTime(driver, GlobalConstants.SHORT_TIMEOUT);
		return isElementDisplayed(driver, UserDashboardPageUI.DYNAMIC_TITLE_BY_POST_TITLE, title);
	}
	
	public boolean isContentDisplayedByPostContent(String content) {
		overrideGlobalImplicitTime(driver, GlobalConstants.SHORT_TIMEOUT);
		return isElementDisplayed(driver, UserDashboardPageUI.DYNAMIC_CONTENT_BY_POST_CONTENT, content);
	}
}
