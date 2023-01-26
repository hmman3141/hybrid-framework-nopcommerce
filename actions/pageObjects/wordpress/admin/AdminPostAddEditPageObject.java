package pageObjects.wordpress.admin;

import org.openqa.selenium.WebDriver;

import commons.wordpress.BasePage_Wordpress;
import pageUIs.wordpress.admin.AdminPostAddEditPageUI;

public class AdminPostAddEditPageObject extends BasePage_Wordpress {
	private WebDriver driver;

	public AdminPostAddEditPageObject(WebDriver driver) {
		this.driver = driver;
	}

	public void sendKeyToTitleOfAddEditPost(String key) {
		waitForElementVisibile(driver, AdminPostAddEditPageUI.ADD_EDIT_TITLE);
		sendKeyToElement(driver, AdminPostAddEditPageUI.ADD_EDIT_TITLE, key);
	}

	public void sendKeyToParagraphOfAddEditPost(String key) {
		waitForElementVisibile(driver, AdminPostAddEditPageUI.ADD_PARAGRAPH_CONTENT_FOR_CONTENT_BLOCK);
		pressKeysToDeleteMessage(driver, AdminPostAddEditPageUI.ADD_PARAGRAPH_CONTENT_FOR_CONTENT_BLOCK);
		sendKeyToElement(driver, AdminPostAddEditPageUI.ADD_PARAGRAPH_CONTENT_FOR_CONTENT_BLOCK, key);
	}

	public void clickToBlockButtonOfAddContent() {
		waitForElementClickable(driver, AdminPostAddEditPageUI.ADD_CONTENT_BLOCK_BUTTON);
		clickToElement(driver, AdminPostAddEditPageUI.ADD_CONTENT_BLOCK_BUTTON);
	}

	public void clickToAddParagraphButtonForContentBlock() {
		waitForElementClickable(driver, AdminPostAddEditPageUI.ADD_PARAGRAPH_BUTTON_FOR_CONTENT_BLOCK);
		clickToElement(driver, AdminPostAddEditPageUI.ADD_PARAGRAPH_BUTTON_FOR_CONTENT_BLOCK);
	}

	public void clickToPublishButton() {
		waitForElementClickable(driver, AdminPostAddEditPageUI.PUBLISH_BUTTON);
		clickToElement(driver, AdminPostAddEditPageUI.PUBLISH_BUTTON);
	}

	public void clickToUpdateButton() {
		waitForElementClickable(driver, AdminPostAddEditPageUI.UPDATE_BUTTON);
		clickToElement(driver, AdminPostAddEditPageUI.UPDATE_BUTTON);
	}

	public void clickToPostPublishButton() {
		waitForElementClickable(driver, AdminPostAddEditPageUI.POST_PUBLISH_BUTTON);
		clickToElement(driver, AdminPostAddEditPageUI.POST_PUBLISH_BUTTON);
	}

	public void hoverAndClickMouseToContent(int number) {
		String num = String.valueOf(number);
		hoverMouseToElementAndClick(driver, AdminPostAddEditPageUI.DYNAMIC_CONTENTS_BY_ORDINAL, num);
	}

	public AdminPostViewPageObject clickToViewPostLink() {
		waitForElementVisibile(driver, AdminPostAddEditPageUI.VIEW_POST_LINK);
		clickToElement(driver, AdminPostAddEditPageUI.VIEW_POST_LINK);
		return PageGeneratorManager.getAdminPostViewPageObject(driver);
	}

	public AdminPostViewPageObject clickToViewUpdatedPostLink() {
		waitForElementVisibile(driver, AdminPostAddEditPageUI.VIEW_UPDATED_POST_LINK);
		clickToElement(driver, AdminPostAddEditPageUI.VIEW_UPDATED_POST_LINK);
		return PageGeneratorManager.getAdminPostViewPageObject(driver);
	}

	public AdminPostSearchPageObject clickToViewAllPostsLink() {
		waitForElementVisibile(driver, AdminPostAddEditPageUI.VIEW_ALL_POSTS_LINK);
		clickToElement(driver, AdminPostAddEditPageUI.VIEW_ALL_POSTS_LINK);
		return PageGeneratorManager.getAdminPostSearchPageObject(driver);
	}

	public boolean isPostSuccessfullyUpdatedMessageDisplayed() {
		return isElementDisplayed(driver, AdminPostAddEditPageUI.POST_SUCCESSFULLY_UPDATED_MESSAGE);
	}
}
