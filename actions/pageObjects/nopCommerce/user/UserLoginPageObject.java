package pageObjects.nopCommerce.user;

import org.openqa.selenium.WebDriver;

import commons.BasePage;
import pageUIs.nopCommerce.user.UserLoginPageUI;

public class UserLoginPageObject extends BasePage {
	private WebDriver driver;

	public UserLoginPageObject(WebDriver driver) {
		super();
		this.driver = driver;
	}

	public void clickOnLoginButton() {
		waitForElementVisibile(driver, UserLoginPageUI.LOGIN_BUTTON);
		clickOnElement(driver, UserLoginPageUI.LOGIN_BUTTON);
	}

	public String getErrorMessageAtEmailInput() {
		waitForElementVisibile(driver, UserLoginPageUI.EMAIL_ERROR_MESSAGE);
		return getElementText(driver, UserLoginPageUI.EMAIL_ERROR_MESSAGE);
	}
	
	public String getErrorMessageByNonExistentEmail() {
		waitForElementVisibile(driver, UserLoginPageUI.EMAIL_NONEXISTENT_ERROR_MESSAGE);
		return getElementText(driver, UserLoginPageUI.EMAIL_NONEXISTENT_ERROR_MESSAGE);
	}

	public void sendKeyToEmailTextbox(String string) {
		waitForElementVisibile(driver, UserLoginPageUI.EMAIL_TEXTBOX);
		sendKeyToElement(driver, UserLoginPageUI.EMAIL_TEXTBOX, string);
	}
	
	public void sendKeyToPasswordTextbox(String string) {
		waitForElementVisibile(driver, UserLoginPageUI.PASSWORD_TEXTBOX);
		sendKeyToElement(driver, UserLoginPageUI.PASSWORD_TEXTBOX, string);
	}
	
	public UserHomePageObject loginAsUser(String email, String password) {
		sendKeyToEmailTextbox(email);
		sendKeyToPasswordTextbox(password);
		clickOnLoginButton();
		return PageGeneratorManager.getUserHomePage(driver);
	}
}
