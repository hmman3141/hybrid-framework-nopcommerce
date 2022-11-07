package PageObjects;

import org.openqa.selenium.WebDriver;

import commons.BasePage;
import pageUIs.LoginPageUI;

public class LoginPageObject extends BasePage {
	private WebDriver driver;

	public LoginPageObject(WebDriver driver) {
		super();
		this.driver = driver;
	}

	public void clickToLoginButton() {
		waitForElementVisibile(driver, LoginPageUI.LOGIN_BUTTON);
		clickToElement(driver, LoginPageUI.LOGIN_BUTTON);
	}

	public String getErrorMessageAtEmailInput() {
		waitForElementVisibile(driver, LoginPageUI.EMAIL_ERROR_MESSAGE);
		return getElementText(driver, LoginPageUI.EMAIL_ERROR_MESSAGE);
	}
	
	public String getErrorMessageByNonExistentEmail() {
		waitForElementVisibile(driver, LoginPageUI.EMAIL_NONEXISTENT_ERROR_MESSAGE);
		return getElementText(driver, LoginPageUI.EMAIL_NONEXISTENT_ERROR_MESSAGE);
	}

	public void sendKeyToEmailTextbox(String string) {
		waitForElementVisibile(driver, LoginPageUI.EMAIL_TEXTBOX);
		sendKeyToElement(driver, LoginPageUI.EMAIL_TEXTBOX, string);
	}
	
	public void sendKeyToPasswordTextbox(String string) {
		waitForElementVisibile(driver, LoginPageUI.PASSWORD_TEXTBOX);
		sendKeyToElement(driver, LoginPageUI.PASSWORD_TEXTBOX, string);
	}
}
