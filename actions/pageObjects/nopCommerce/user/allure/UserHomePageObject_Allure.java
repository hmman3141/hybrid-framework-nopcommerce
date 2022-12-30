package pageObjects.nopCommerce.user.allure;

import org.openqa.selenium.WebDriver;

import commons.BasePage;
import io.qameta.allure.Step;
import pageUIs.nopCommerce.user.BasePageUI;
import pageUIs.nopCommerce.user.UserHomePageUI;

public class UserHomePageObject_Allure extends BasePage {
	private WebDriver driver;

	public UserHomePageObject_Allure(WebDriver driver) {
		super();
		this.driver = driver;
	}

	@Step(value = "Click to register link on bar")
	public UserRegisterPageObject_Allure clickToRegisterLink() {
		waitForElementClickable(driver, UserHomePageUI.REGISTER_LINK);
		clickToElement(driver, UserHomePageUI.REGISTER_LINK);
		return new UserRegisterPageObject_Allure(driver);
	}
	
	@Step(value = "Click to login link on bar")
	public UserLoginPageObject_Allure clickToLoginLink() {
		waitForElementVisibile(driver, UserHomePageUI.LOGIN_LINK);
		clickToElement(driver, UserHomePageUI.LOGIN_LINK);
		return new UserLoginPageObject_Allure(driver);
	}

	@Step(value = "Click to logout link on bar")
	public UserHomePageObject_Allure clickToLogoutAtUserPage_Allure(WebDriver driver) {
		waitForElementVisibile(driver, BasePageUI.LOGOUT_LINK_AT_USER);
		clickToElement(driver, BasePageUI.LOGOUT_LINK_AT_USER);
		return PageGeneratorManager_Allure.getUserHomePage(driver);
	}
	
	@Step(value = "Verify account linked")
	public boolean isAccountLink() {
		return isElementPresence(driver, UserHomePageUI.ACCOUNT_LINK);
	}
}
