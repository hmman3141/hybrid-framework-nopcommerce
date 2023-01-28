package pageObjects.wordpress.admin;

import org.openqa.selenium.WebDriver;

import commons.wordpress.BasePage_Wordpress;
import pageUIs.wordpress.admin.AdminDashboardPageUI;

public class AdminDashboardPageObject extends BasePage_Wordpress {
	private WebDriver driver;

	public AdminDashboardPageObject(WebDriver driver) {
		this.driver = driver;
	}
	
	public String getDashboardPanelText() {
		return getElementText(driver, AdminDashboardPageUI.WELCOME_PANEL);
	}
	
	public AdminPostSearchPageObject clickOnPostsSidebarMenu() {
		waitForElementVisibile(driver, AdminDashboardPageUI.POSTS_SIDEBAR_MENU);
		clickOnElement(driver, AdminDashboardPageUI.POSTS_SIDEBAR_MENU);
		return AdminPageGeneratorManager.getAdminPostSearchPageObject(driver);
	}
}
