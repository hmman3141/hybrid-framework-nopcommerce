package pageObjects.wordpress.admin;

import org.openqa.selenium.WebDriver;

import commons.wordpress.BasePage_Wordpress;
import pageUIs.wordpress.admin.AdminLoginPageUI;

public class AdminLoginPageObject extends BasePage_Wordpress {
	private WebDriver driver;

	public AdminLoginPageObject(WebDriver driver) {
		this.driver = driver;
	}

	public void sendKeyToUsernameTextbox(String key) {
		waitForElementVisibile(driver, AdminLoginPageUI.USERNAME_TEXTBOX);
		sendKeyToElement(driver, AdminLoginPageUI.USERNAME_TEXTBOX, key);
	}
	
	public void sendKeyToPasswordTextbox(String key) {
		waitForElementVisibile(driver, AdminLoginPageUI.PASSWORD_TEXTBOX);
		sendKeyToElement(driver, AdminLoginPageUI.PASSWORD_TEXTBOX, key);
	}
	
	public AdminDashboardPageObject clickOnLoginButton() {
		waitForElementVisibile(driver, AdminLoginPageUI.LOGIN_BUTTON);
		clickOnElement(driver, AdminLoginPageUI.LOGIN_BUTTON);
		return AdminPageGeneratorManager.getAdminDashboardPageObject(driver);
	}
}
