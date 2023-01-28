package pageObjects.nopCommerce.user;

import org.openqa.selenium.WebDriver;

import commons.BasePage;
import pageUIs.nopCommerce.user.UserChangePasswordPageUI;

public class UserChangePasswordPageObject extends BasePage {
	private WebDriver driver;

	public UserChangePasswordPageObject(WebDriver driver) {
		super();
		this.driver = driver;
	}
	
	public void clickOnChangePasswordButton() {
		waitForElementClickable(driver, UserChangePasswordPageUI.CHANGE_PASSWORD_BUTTON);
		clickOnElement(driver, UserChangePasswordPageUI.CHANGE_PASSWORD_BUTTON);
	}
	
	public void clickOnCloseSuccessfulPasswordChangeButton() {
		waitForElementClickable(driver, UserChangePasswordPageUI.CLOSE_PASSWORD_SUCCESSFUL_CHANGE_NOTIFICATION);
		clickOnElement(driver, UserChangePasswordPageUI.CLOSE_PASSWORD_SUCCESSFUL_CHANGE_NOTIFICATION);
	}

	public void sendKeyToOldPasswordTextbox(String key) {
		waitForElementVisibile(driver, UserChangePasswordPageUI.OLD_PASSWORD);
		sendKeyToElement(driver, UserChangePasswordPageUI.OLD_PASSWORD, key);
	}
	
	public void sendKeyToNewPasswordTextbox(String key) {
		waitForElementVisibile(driver, UserChangePasswordPageUI.NEW_PASSWORD);
		sendKeyToElement(driver, UserChangePasswordPageUI.NEW_PASSWORD, key);
	}
	
	public void sendKeyToConfirmNewPasswordTextbox(String key) {
		waitForElementVisibile(driver, UserChangePasswordPageUI.CONFIRM_NEW_PASSWORD);
		sendKeyToElement(driver, UserChangePasswordPageUI.CONFIRM_NEW_PASSWORD, key);
	}
}
