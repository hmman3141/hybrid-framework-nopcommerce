package pageObjects.nopCommerce.user;

import org.openqa.selenium.WebDriver;

import commons.BasePage;
import pageUIs.nopCommerce.user.UserHomePageUI;

public class UserHomePageObject extends BasePage {
	private WebDriver driver;

	public UserHomePageObject(WebDriver driver) {
		super();
		this.driver = driver;
	}

	public UserRegisterPageObject clickToRegisterLink() {
		waitForElementClickable(driver, UserHomePageUI.REGISTER_LINK);
		clickToElement(driver, UserHomePageUI.REGISTER_LINK);
		return new UserRegisterPageObject(driver);
	}
	
	public UserLoginPageObject clickToLoginLink() {
		waitForElementVisibile(driver, UserHomePageUI.LOGIN_LINK);
		clickToElement(driver, UserHomePageUI.LOGIN_LINK);
		return new UserLoginPageObject(driver);
	}
	
	public UserCustomerInfoPageObject clickToMyAccountLink() {
		waitForElementVisibile(driver, UserHomePageUI.ACCOUNT_LINK);
		clickToElement(driver, UserHomePageUI.ACCOUNT_LINK);
		return new UserCustomerInfoPageObject(driver);
	}
	
	public boolean isAccountLink() {
		return isElementPresence(driver, UserHomePageUI.ACCOUNT_LINK);
	}
}
