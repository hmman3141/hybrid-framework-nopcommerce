package pageObjects.nopCommerce.admin;

import org.openqa.selenium.WebDriver;

import commons.BasePage;
import pageObjects.nopCommerce.user.PageGeneratorManager;
import pageUIs.nopCommerce.admin.AdminLoginPageUI;

public class AdminLoginPageObject extends BasePage {
	private WebDriver driver;

	public AdminLoginPageObject(WebDriver driver) {
		this.driver = driver;
	}
	
	public void sendKeyToEmailTextbox(String string) {
		waitForElementVisibile(driver, AdminLoginPageUI.EMAIL_TEXTBOX);
		sendKeyToElement(driver, AdminLoginPageUI.EMAIL_TEXTBOX, string);
	}
	
	public void sendKeyToPasswordTextbox(String string) {
		waitForElementVisibile(driver, AdminLoginPageUI.PASSWORD_TEXTBOX);
		sendKeyToElement(driver, AdminLoginPageUI.PASSWORD_TEXTBOX, string);
	}
	
	public void clickOnLoginButton() {
		waitForElementVisibile(driver, AdminLoginPageUI.LOGIN_BUTTON);
		clickOnElement(driver, AdminLoginPageUI.LOGIN_BUTTON);
	}
	
	public String getErrorMessageAtEmailInput() {
		waitForElementVisibile(driver, AdminLoginPageUI.EMAIL_ERROR_MESSAGE);
		return getElementText(driver, AdminLoginPageUI.EMAIL_ERROR_MESSAGE);
	}
	
	public String getErrorMessageByNonExistentEmail() {
		waitForElementVisibile(driver, AdminLoginPageUI.EMAIL_NONEXISTENT_ERROR_MESSAGE);
		return getElementText(driver, AdminLoginPageUI.EMAIL_NONEXISTENT_ERROR_MESSAGE);
	}

	public AdminDashboardPageObject loginAsAdmin(String adminEmail, String adminPassword) {
		sendKeyToEmailTextbox(adminEmail);
		sendKeyToPasswordTextbox(adminPassword);
		clickOnLoginButton();
		return PageGeneratorManager.getPageGenerator().getAdminDashboardPage(driver);
	}
}
