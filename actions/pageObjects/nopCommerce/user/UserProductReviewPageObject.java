package pageObjects.nopCommerce.user;

import org.openqa.selenium.WebDriver;

import commons.BasePage;
import pageUIs.nopCommerce.user.UserProductReviewPageUI;

public class UserProductReviewPageObject extends BasePage {
	private WebDriver driver;

	public UserProductReviewPageObject(WebDriver driver) {
		super();
		this.driver = driver;
	}

	public void sendKeyToReviewTitleTextbox(String key) {
		waitForElementVisibile(driver, UserProductReviewPageUI.REVIEW_TITLE_TEXTBOX);
		sendKeyToElement(driver, UserProductReviewPageUI.REVIEW_TITLE_TEXTBOX, key);
	}
	
	public void sendKeyToReviewTextTextbox(String key) {
		waitForElementVisibile(driver, UserProductReviewPageUI.REVIEW_TEXT_TEXTBOX);
		sendKeyToElement(driver, UserProductReviewPageUI.REVIEW_TEXT_TEXTBOX, key);
	}
	
	public void clickToRatingPoint1RadioButton() {
		waitForElementVisibile(driver, UserProductReviewPageUI.REVIEW_RATING_POINT_1);
		clickToElement(driver, UserProductReviewPageUI.REVIEW_RATING_POINT_1);
	}
	
	public void clickToSubmitReviewButton() {
		waitForElementVisibile(driver, UserProductReviewPageUI.SUBMIT_REVIEW_BUTTON);
		clickToElement(driver, UserProductReviewPageUI.SUBMIT_REVIEW_BUTTON);
	}
}
