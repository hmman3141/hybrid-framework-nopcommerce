package pageObjects.nopCommerce.user.allure;

import org.openqa.selenium.WebDriver;

import commons.BasePage;
import io.qameta.allure.Step;
import pageUIs.nopCommerce.user.UserLoginPageUI;

public class UserLoginPageObject_Allure extends BasePage {
	private WebDriver driver;

	public UserLoginPageObject_Allure(WebDriver driver) {
		super();
		this.driver = driver;
	}

	public void clickToLoginButton() {
		waitForElementVisibile(driver, UserLoginPageUI.LOGIN_BUTTON);
		clickToElement(driver, UserLoginPageUI.LOGIN_BUTTON);
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
	
	@Step(value = "Login as user with information: {0}/{1}")
	public UserHomePageObject_Allure loginAsUser(String email, String password) {
		sendKeyToEmailTextbox(email);
		sendKeyToPasswordTextbox(password);
		clickToLoginButton();
		return PageGeneratorManager_Allure.getUserHomePage(driver);
	}
}
