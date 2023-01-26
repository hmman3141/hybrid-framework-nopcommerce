package pageObjects.wordpress.admin;

import org.openqa.selenium.WebDriver;

import commons.wordpress.BasePage_Wordpress;
import pageUIs.wordpress.admin.AdminPostSearchPageUI;

public class AdminPostSearchPageObject extends BasePage_Wordpress {
	private WebDriver driver;

	public AdminPostSearchPageObject(WebDriver driver) {
		this.driver = driver;
	}
	
	public AdminPostAddEditPageObject clickToAddNewPost() {
		waitForElementVisibile(driver, AdminPostSearchPageUI.ADD_NEW_POST);
		clickToElement(driver, AdminPostSearchPageUI.ADD_NEW_POST);
		return new AdminPostAddEditPageObject(driver);
	}
	
	public AdminPostAddEditPageObject clickToEditButtonOfRecentlyAddedPost() {
		waitForElementVisibile(driver, AdminPostSearchPageUI.EDIT_BUTTON_OF_RECENTLY_ADDED_POST);
		clickToElement(driver, AdminPostSearchPageUI.EDIT_BUTTON_OF_RECENTLY_ADDED_POST);
		return new AdminPostAddEditPageObject(driver);
	}
	
	public String getRecentlyAddedPostTitle() {
		waitForElementVisibile(driver, AdminPostSearchPageUI.TITLE_OF_RECENTLY_ADDED_POST);
		return getElementText(driver, AdminPostSearchPageUI.TITLE_OF_RECENTLY_ADDED_POST);
	}
	
	public void hoverMouseToTitleOfRecentlyAddedPost() {
		waitForElementVisibile(driver, AdminPostSearchPageUI.TITLE_OF_RECENTLY_ADDED_POST);
		hoverMouseToElement(driver, AdminPostSearchPageUI.TITLE_OF_RECENTLY_ADDED_POST);
	}
}
