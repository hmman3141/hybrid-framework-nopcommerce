package pageObjects.wordpress.admin;

import org.openqa.selenium.WebDriver;

import commons.wordpress.BasePage_Wordpress;
import pageUIs.wordpress.admin.AdminPostViewPageUI;

public class AdminPostViewPageObject extends BasePage_Wordpress{
	private WebDriver driver;

	public AdminPostViewPageObject(WebDriver driver) {
		this.driver = driver;
	}
	
	public String getViewTitleText() {
		return getElementText(driver, AdminPostViewPageUI.VIEW_TITLE);
	}
	
	public String getViewContentText() {
		return getElementText(driver, AdminPostViewPageUI.VIEW_CONTENT);
	}
}
