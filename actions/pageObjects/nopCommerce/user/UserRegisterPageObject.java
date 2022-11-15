package pageObjects.nopCommerce.user;

import org.openqa.selenium.WebDriver;

import commons.BasePage;
import pageUIs.nopCommerce.user.BasePageUI;
import pageUIs.nopCommerce.user.UserRegisterPageUI;

public class UserRegisterPageObject extends BasePage {
	private WebDriver driver;

	public UserRegisterPageObject(WebDriver driver) {
		super();
		this.driver = driver;
	}

	public void clickToRegisterButton() {
		waitForElementClickable(driver, UserRegisterPageUI.REGISTER_BUTTON);
		clickToElement(driver, UserRegisterPageUI.REGISTER_BUTTON);
	}

	public void clickToLogoutButton() {
		waitForElementClickable(driver, BasePageUI.LOGOUT_LINK_AT_USER);
		clickToElement(driver, BasePageUI.LOGOUT_LINK_AT_USER);
	}

	public String getErrorMessageAtFirstNameInput() {
		waitForElementVisibile(driver, UserRegisterPageUI.FIRST_NAME_ERROR_MESSAGE);
		return getElementText(driver, UserRegisterPageUI.FIRST_NAME_ERROR_MESSAGE);
	}

	public String getErrorMessageAtLastNameInput() {
		waitForElementVisibile(driver, UserRegisterPageUI.LAST_NAME_ERROR_MESSAGE);
		return getElementText(driver, UserRegisterPageUI.LAST_NAME_ERROR_MESSAGE);
	}

	public String getErrorMessageAtPasswordNameInput() {
		waitForElementVisibile(driver, UserRegisterPageUI.PASSWORD_ERROR_MESSAGE);
		return getElementText(driver, UserRegisterPageUI.PASSWORD_ERROR_MESSAGE);
	}

	public String getErrorMessageAtConfirmPasswordInput() {
		waitForElementVisibile(driver, UserRegisterPageUI.CONFIRM_PASSWORD_ERROR_MESSAGE);
		return getElementText(driver, UserRegisterPageUI.CONFIRM_PASSWORD_ERROR_MESSAGE);
	}

	public String getErrorMessageAtEmailInput() {
		waitForElementVisibile(driver, UserRegisterPageUI.EMAIL_ERROR_MESSAGE);
		return getElementText(driver, UserRegisterPageUI.EMAIL_ERROR_MESSAGE);
	}

	public String getRegisterSuccessMessage() {
		waitForElementVisibile(driver, UserRegisterPageUI.REGISTER_SUCCESS_MESSAGE);
		return getElementText(driver, UserRegisterPageUI.REGISTER_SUCCESS_MESSAGE);
	}

	public String getRegisterDuplicatedMessage() {
		waitForElementVisibile(driver, UserRegisterPageUI.EMAIL_DUPLICATED_MESSAGE);
		return getElementText(driver, UserRegisterPageUI.EMAIL_DUPLICATED_MESSAGE);
	}

	public void sendKeyToEmailTextbox(String string) {
		waitForElementVisibile(driver, UserRegisterPageUI.EMAIL_TEXTBOX);
		sendKeyToElement(driver, UserRegisterPageUI.EMAIL_TEXTBOX, string);
	}

	public void sendKeyToFirstNameTextbox(String string) {
		waitForElementVisibile(driver, UserRegisterPageUI.FIRST_NAME_TEXTBOX);
		sendKeyToElement(driver, UserRegisterPageUI.FIRST_NAME_TEXTBOX, string);
	}

	public void sendKeyToLastNameTextbox(String string) {
		waitForElementVisibile(driver, UserRegisterPageUI.LAST_NAME_TEXTBOX);
		sendKeyToElement(driver, UserRegisterPageUI.LAST_NAME_TEXTBOX, string);
	}

	public void sendKeyToPasswordTextbox(String string) {
		waitForElementVisibile(driver, UserRegisterPageUI.PASSWORD_TEXTBOX);
		sendKeyToElement(driver, UserRegisterPageUI.PASSWORD_TEXTBOX, string);
	}

	public void sendKeyToConfirmPasswordTextbox(String string) {
		waitForElementVisibile(driver, UserRegisterPageUI.CONFIRM_PASSWORD_TEXTBOX);
		sendKeyToElement(driver, UserRegisterPageUI.CONFIRM_PASSWORD_TEXTBOX, string);
	}
}
