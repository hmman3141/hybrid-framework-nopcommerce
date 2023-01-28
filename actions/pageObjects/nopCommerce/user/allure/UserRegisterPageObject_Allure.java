package pageObjects.nopCommerce.user.allure;

import org.openqa.selenium.WebDriver;

import commons.BasePage;
import io.qameta.allure.Step;
import pageUIs.nopCommerce.user.UserRegisterPageUI;

public class UserRegisterPageObject_Allure extends BasePage {
	private WebDriver driver;

	public UserRegisterPageObject_Allure(WebDriver driver) {
		super();
		this.driver = driver;
	}

	@Step(value = "Click on register button")
	public void clickOnRegisterButton() {
		waitForElementClickable(driver, UserRegisterPageUI.REGISTER_BUTTON);
		clickOnElement(driver, UserRegisterPageUI.REGISTER_BUTTON);
	}

	@Step(value = "Verify register success message")
	public String getRegisterSuccessMessage() {
		waitForElementVisibile(driver, UserRegisterPageUI.REGISTER_SUCCESS_MESSAGE);
		return getElementText(driver, UserRegisterPageUI.REGISTER_SUCCESS_MESSAGE);
	}

	@Step(value = "Send key to email textbox with value '{0}'")
	public void sendKeyToEmailTextbox(String string) {
		waitForElementVisibile(driver, UserRegisterPageUI.EMAIL_TEXTBOX);
		sendKeyToElement(driver, UserRegisterPageUI.EMAIL_TEXTBOX, string);
	}

	@Step(value = "Send key to first name text box with value '{0}'")
	public void sendKeyToFirstNameTextbox(String string) {
		waitForElementVisibile(driver, UserRegisterPageUI.FIRST_NAME_TEXTBOX);
		sendKeyToElement(driver, UserRegisterPageUI.FIRST_NAME_TEXTBOX, string);
	}

	@Step(value = "Send key to last name text box with value '{0}'")
	public void sendKeyToLastNameTextbox(String string) {
		waitForElementVisibile(driver, UserRegisterPageUI.LAST_NAME_TEXTBOX);
		sendKeyToElement(driver, UserRegisterPageUI.LAST_NAME_TEXTBOX, string);
	}

	@Step(value = "Send key to password text box with value '{0}'")
	public void sendKeyToPasswordTextbox(String string) {
		waitForElementVisibile(driver, UserRegisterPageUI.PASSWORD_TEXTBOX);
		sendKeyToElement(driver, UserRegisterPageUI.PASSWORD_TEXTBOX, string);
	}

	@Step(value = "Send key to confirm password text box with value '{0}'")
	public void sendKeyToConfirmPasswordTextbox(String string) {
		waitForElementVisibile(driver, UserRegisterPageUI.CONFIRM_PASSWORD_TEXTBOX);
		sendKeyToElement(driver, UserRegisterPageUI.CONFIRM_PASSWORD_TEXTBOX, string);
	}
}
