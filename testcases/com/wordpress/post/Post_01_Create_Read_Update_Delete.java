package com.wordpress.post;

import static reportConfig.TestListener.endTest;
import static reportConfig.TestListener.startTest;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import commons.BaseTest;
import pageObjects.wordpress.admin.AdminDashboardPageObject;
import pageObjects.wordpress.admin.AdminLoginPageObject;
import pageObjects.wordpress.admin.AdminPostAddEditPageObject;
import pageObjects.wordpress.admin.AdminPostSearchPageObject;
import pageObjects.wordpress.admin.AdminPostViewPageObject;
import pageObjects.wordpress.admin.PageGeneratorManager;
import pageUIs.wordpress.admin.BasePageUI;

import static reportConfig.TestListener.log4J;

public class Post_01_Create_Read_Update_Delete extends BaseTest {
	private WebDriver driver;
	private String username, password, title, content, editedTitle, editedContent;
	private AdminLoginPageObject adminLoginPage;
	private AdminDashboardPageObject adminDashboardPage;
	private AdminPostSearchPageObject adminPostSearchPage;
	private AdminPostAddEditPageObject adminPostAddEditPage;
	private AdminPostViewPageObject adminPostViewPage;

	@Parameters("Browser")
	@BeforeClass()
	public void beforeClass(String browserName) {
		driver = getBrowserDriver(browserName, BasePageUI.ADMIN_URL);
		adminLoginPage = PageGeneratorManager.getAdminLoginPageObject(driver);

		username = "automationfc";
		password = "automationfc";
		title = "Title for testing " + getRandomNumber();
		content = "Content for testing";
		editedTitle = "Edited " + title;
		editedContent = "Edited " + content;

		startTest("Pre-condition", "Login");
		log4J.Info("Pre-condition - Step 01: Send to Username textbox with value '" + username + "'");
		adminLoginPage.sendKeyToUsernameTextbox(username);
		log4J.Info("Pre-condition - Step 02: Send to Password textbox with value '" + password + "'");
		adminLoginPage.sendKeyToPasswordTextbox(password);
		log4J.Info("Pre-condition - Step 03: Click to login button");
		adminDashboardPage = adminLoginPage.clickToLoginButton();
		log4J.Info("Pre-condition - Step 04: Verify dashboard panel text with value 'Welcome to WordPress!'");
		verifyEquals(adminDashboardPage.getDashboardPanelText(), "Welcome to WordPress!");
		log4J.Info("Pre-condition - Step 05: Click to Post sidebar menu");
		adminPostSearchPage = adminDashboardPage.clickToPostsSidebarMenu();
		endTest();
	}

	@Test
	public void TC_01_CreateNewPost() {
		log4J.Info("Create New Post - Step 01: Click to add new post button");
		adminPostAddEditPage = adminPostSearchPage.clickToAddNewPost();
		log4J.Info("Create New Post - Step 02: Send to add title textbox with value '" + title + "'");
		adminPostAddEditPage.sendKeyToTitleOfAddEditPost(title);
		log4J.Info("Create New Post - Step 03: Click to new add content button");
		adminPostAddEditPage.clickToBlockButtonOfAddContent();
		log4J.Info("Create New Post - Step 04: Click to add paragraph button for content block");
		adminPostAddEditPage.clickToAddParagraphButtonForContentBlock();
		log4J.Info("Create New Post - Step 05: Send to add content textbox with value '" + content + "'");
		adminPostAddEditPage.sendKeyToParagraphOfAddEditPost(content);
		log4J.Info("Create New Post - Step 06: Click to publish button");
		adminPostAddEditPage.clickToPublishButton();
		log4J.Info("Create New Post - Step 07: Re-Click to publish button");
		adminPostAddEditPage.clickToPostPublishButton();
		log4J.Info("Create New Post - Step 08: Click view post button");
		adminPostViewPage = adminPostAddEditPage.clickToViewPostLink();
		log4J.Info("Create New Post - Step 09: Verify title with value '" + title + "'");
		verifyEquals(adminPostViewPage.getViewTitleText(), title);
		log4J.Info("Create New Post - Step 10: Verify content with value '" + content + "'");
		verifyEquals(adminPostViewPage.getViewContentText(), content);
		log4J.Info("Create New Post - Step 11: Back to Add new post page");
		adminPostViewPage.back(driver);
		log4J.Info("Create New Post - Step 12: Click to view posts icon");
		adminPostSearchPage = adminPostAddEditPage.clickToViewAllPostsLink();
		log4J.Info("Create New Post - Step 13: Verify title of recently added post with value '" + title + "'");
		verifyEquals(adminPostSearchPage.getRecentlyAddedPostTitle(), title);
	}

	@Test
	public void TC_02_EditPost() {
		log4J.Info("Update Post - Step 01: Hover mouse to title of recently post");
		adminPostSearchPage.hoverMouseToTitleOfRecentlyAddedPost();
		log4J.Info("Update Post - Step 02: Click to edit button of recently post");
		adminPostAddEditPage = adminPostSearchPage.clickToEditButtonOfRecentlyAddedPost();
		log4J.Info("Update Post - Step 03: Send to add title textbox with value '" + editedTitle + "'");
		adminPostAddEditPage.sendKeyToTitleOfAddEditPost(editedTitle);
		log4J.Info("Update Post - Step 04: Hover and click mouse to first content");
		adminPostAddEditPage.hoverAndClickMouseToContent(1);
		log4J.Info("Update Post - Step 05: Send to add content textbox with value '" + editedContent + "'");
		adminPostAddEditPage.sendKeyToParagraphOfAddEditPost(editedContent);
		log4J.Info("Update Post - Step 06: Click to update button");
		adminPostAddEditPage.clickToUpdateButton();
		log4J.Info("Update Post - Step 07: Verify post successfully updated message displayed");
		verifyTrue(adminPostAddEditPage.isPostSuccessfullyUpdatedMessageDisplayed());
		log4J.Info("Update Post - Step 08: Click view post button");
		adminPostViewPage = adminPostAddEditPage.clickToViewUpdatedPostLink();
		log4J.Info("Update Post - Step 09: Verify title with value '" + editedTitle + "'");
		verifyEquals(adminPostViewPage.getViewTitleText(), editedTitle);
		log4J.Info("Update Post - Step 10: Verify content with value '" + editedContent + "'");
		verifyEquals(adminPostViewPage.getViewContentText(), editedContent);
		log4J.Info("Update Post - Step 11: Back to Search post page");
		adminPostViewPage.back(driver);
		log4J.Info("Update Post - Step 12: Click to view posts icon");
		adminPostSearchPage = adminPostAddEditPage.clickToViewAllPostsLink();
		log4J.Info("Update Post - Step 13: Verify title of recently edited post with value '" + editedTitle + "'");
		verifyEquals(adminPostSearchPage.getRecentlyAddedPostTitle(), editedTitle);
	}

	@AfterClass(alwaysRun = true)
	public void afterClass() {
		driver.close();
	}
}
