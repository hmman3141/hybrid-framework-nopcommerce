package pageObjects.nopCommerce.user;

import org.openqa.selenium.WebDriver;

import commons.BasePage;
import pageUIs.nopCommerce.user.UserProductInformationPageUI;

public class UserProductInformationPageObject extends BasePage {
	private WebDriver driver;

	public UserProductInformationPageObject(WebDriver driver) {
		super();
		this.driver = driver;
	}
	
	public UserProductReviewPageObject clickToLinkAddYourReview() {
		waitForElementVisibile(driver, UserProductInformationPageUI.ADD_YOUR_REVIEW_LINK);
		clickToElement(driver, UserProductInformationPageUI.ADD_YOUR_REVIEW_LINK);
		return PageGeneratorManager.getUserProductReviewPage(driver);
	}
}
