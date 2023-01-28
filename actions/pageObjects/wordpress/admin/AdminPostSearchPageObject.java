package pageObjects.wordpress.admin;

import org.openqa.selenium.WebDriver;

import commons.wordpress.BasePage_Wordpress;
import pageUIs.wordpress.admin.AdminPostSearchPageUI;

public class AdminPostSearchPageObject extends BasePage_Wordpress {
	private WebDriver driver;

	public AdminPostSearchPageObject(WebDriver driver) {
		this.driver = driver;
	}

	public AdminPostAddEditPageObject clickOnAddNewPost() {
		waitForElementVisibile(driver, AdminPostSearchPageUI.ADD_NEW_POST);
		clickOnElement(driver, AdminPostSearchPageUI.ADD_NEW_POST);
		return new AdminPostAddEditPageObject(driver);
	}

	public AdminPostAddEditPageObject clickOnEditButtonOfRecentlyAddedPost() {
		waitForElementVisibile(driver, AdminPostSearchPageUI.EDIT_BUTTON_OF_RECENTLY_ADDED_POST);
		clickOnElement(driver, AdminPostSearchPageUI.EDIT_BUTTON_OF_RECENTLY_ADDED_POST);
		return new AdminPostAddEditPageObject(driver);
	}

	public void clickOnBulkActionsApplyButton() {
		waitForElementVisibile(driver, AdminPostSearchPageUI.BULK_ACTIONS_APPLY_BUTTON);
		clickOnElement(driver, AdminPostSearchPageUI.BULK_ACTIONS_APPLY_BUTTON);
	}
	
	public void clickOnSelectAllPostsButton() {
		waitForElementVisibile(driver, AdminPostSearchPageUI.SELECT_ALL_BUTTON);
		clickOnElement(driver, AdminPostSearchPageUI.SELECT_ALL_BUTTON);
	}
	
	public void clickOnTrashLink() {
		waitForElementVisibile(driver, AdminPostSearchPageUI.TRASH_LINK);
		clickOnElement(driver, AdminPostSearchPageUI.TRASH_LINK);
	}
	
	public void clickOnEmptyTrashButton() {
		waitForElementVisibile(driver, AdminPostSearchPageUI.EMPTY_TRASH_BUTTON);
		clickOnElement(driver, AdminPostSearchPageUI.EMPTY_TRASH_BUTTON);
	}

	public void selectMoveToTrashInBulkActions() {
		waitForElementVisibile(driver, AdminPostSearchPageUI.BULK_ACTIONS_SELECT);
		selectItemInDefaultDropdown(driver, AdminPostSearchPageUI.BULK_ACTIONS_SELECT, "Move to Trash");
	}

	public String getRecentlyAddedPostTitle() {
		waitForElementVisibile(driver, AdminPostSearchPageUI.TITLE_OF_RECENTLY_ADDED_POST);
		return getElementText(driver, AdminPostSearchPageUI.TITLE_OF_RECENTLY_ADDED_POST);
	}
	
	public String getNoPostsMessage() {
		waitForElementVisibile(driver, AdminPostSearchPageUI.NO_POSTS_MESSAGE);
		return getElementText(driver, AdminPostSearchPageUI.NO_POSTS_MESSAGE);
	}

	public void hoverMouseToTitleOfRecentlyAddedPost() {
		waitForElementVisibile(driver, AdminPostSearchPageUI.TITLE_OF_RECENTLY_ADDED_POST);
		hoverMouseToElement(driver, AdminPostSearchPageUI.TITLE_OF_RECENTLY_ADDED_POST);
	}
}
