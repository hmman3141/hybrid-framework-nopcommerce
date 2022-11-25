package pageObjects.nopCommerce.user;

import org.openqa.selenium.WebDriver;

import commons.BasePage;
import pageUIs.nopCommerce.user.UserMyProductReviewPageUI;

public class UserMyProductReviewPageObject extends BasePage {
	private WebDriver driver;

	public UserMyProductReviewPageObject(WebDriver driver) {
		super();
		this.driver = driver;
	}
	
	public String getTextFromRecentReviewTitle() {
		waitForElementVisibile(driver, UserMyProductReviewPageUI.RECENT_REVIEW_TITLE);
		return getElementText(driver, UserMyProductReviewPageUI.RECENT_REVIEW_TITLE);
	}
	
	public String getTextFromRecentReviewText() {
		waitForElementVisibile(driver, UserMyProductReviewPageUI.RECENT_REVIEW_TEXT);
		return getElementText(driver, UserMyProductReviewPageUI.RECENT_REVIEW_TEXT);
	}
}
