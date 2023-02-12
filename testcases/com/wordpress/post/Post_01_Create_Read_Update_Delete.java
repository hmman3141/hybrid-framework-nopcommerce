package com.wordpress.post;

import static reportConfig.TestListener.endTest;
import static reportConfig.TestListener.startTest;

import org.aeonbits.owner.ConfigFactory;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.wordpress.data.UserData;

import commons.BaseTest;
import pageObjects.wordpress.admin.AdminDashboardPageObject;
import pageObjects.wordpress.admin.AdminLoginPageObject;
import pageObjects.wordpress.admin.AdminPostAddEditPageObject;
import pageObjects.wordpress.admin.AdminPostSearchPageObject;
import pageObjects.wordpress.admin.AdminPostViewPageObject;
import pageObjects.wordpress.user.UserDashboardPageObject;
import pageObjects.wordpress.user.UserPageGeneratorManager;
import pageObjects.wordpress.admin.AdminPageGeneratorManager;
import utilities.Environment;

import static reportConfig.TestListener.log4J;

public class Post_01_Create_Read_Update_Delete extends BaseTest {
	private WebDriver driver;
	private Environment env;
	private String title, content, editedTitle, editedContent, noPostMessage;
	private UserData userData;
	private AdminLoginPageObject adminLoginPage;
	private AdminDashboardPageObject adminDashboardPage;
	private AdminPostSearchPageObject adminPostSearchPage;
	private AdminPostAddEditPageObject adminPostAddEditPage;
	private AdminPostViewPageObject adminPostViewPage;
	private UserDashboardPageObject userDashboardPage;

	@Parameters({ "Browser", "Environment" })
	@BeforeClass()
	public void beforeClass(String browserName, String envName) {
		ConfigFactory.setProperty("env", envName);
		env = ConfigFactory.create(Environment.class);
		driver = getBrowserDriver(browserName, env.getAppUrl());
		adminLoginPage = AdminPageGeneratorManager.getAdminLoginPageObject(driver);

		userData = UserData.getUserData("userData.json");
		title = "Title for testing " + getRandomNumber();
		content = "Content for testing";
		editedTitle = "Edited " + title;
		editedContent = "Edited " + content;
		noPostMessage = "No posts found in Trash.";

		startTest("Pre-condition", "Login");
		log4J.Info("Pre-condition - Step 01: Send to Username textbox with value '" + userData.getUsername() + "'");
		adminLoginPage.sendKeyToUsernameTextbox(userData.getUsername());
		log4J.Info("Pre-condition - Step 02: Send to Password textbox with value '" + userData.getPassword() + "'");
		adminLoginPage.sendKeyToPasswordTextbox(userData.getPassword());
		log4J.Info("Pre-condition - Step 03: Click on login button");
		adminDashboardPage = adminLoginPage.clickOnLoginButton();
		log4J.Info("Pre-condition - Step 04: Verify dashboard panel text with value 'Welcome to WordPress!'");
		verifyEquals(adminDashboardPage.getDashboardPanelText(), "Welcome to WordPress!");
		log4J.Info("Pre-condition - Step 05: Click on Post sidebar menu");
		adminPostSearchPage = adminDashboardPage.clickOnPostsSidebarMenu();
		endTest();
	}

	@Test
	public void TC_01_CreateNewPost() {
		log4J.Info("Create New Post - Step 01: Click on add new post button");
		adminPostAddEditPage = adminPostSearchPage.clickOnAddNewPost();
		log4J.Info("Create New Post - Step 02: Send to add title textbox with value '" + title + "'");
		adminPostAddEditPage.sendKeyToTitleOfAddEditPost(title);
		log4J.Info("Create New Post - Step 03: Click on new add content button");
		adminPostAddEditPage.clickOnBlockButtonOfAddContent();
		log4J.Info("Create New Post - Step 04: Click on add paragraph button for content block");
		adminPostAddEditPage.clickOnAddParagraphButtonForContentBlock();
		log4J.Info("Create New Post - Step 05: Send to add content textbox with value '" + content + "'");
		adminPostAddEditPage.sendKeyToParagraphOfAddEditPost(content);
		log4J.Info("Create New Post - Step 06: Click on publish button");
		adminPostAddEditPage.clickOnPublishButton();
		log4J.Info("Create New Post - Step 07: Re-Click on publish button");
		adminPostAddEditPage.clickOnPostPublishButton();
		log4J.Info("Create New Post - Step 08: Click view post button");
		adminPostViewPage = adminPostAddEditPage.clickOnViewPostLink();
		log4J.Info("Create New Post - Step 09: Verify title with value '" + title + "'");
		verifyEquals(adminPostViewPage.getViewTitleText(), title);
		log4J.Info("Create New Post - Step 10: Verify content with value '" + content + "'");
		verifyEquals(adminPostViewPage.getViewContentText(), content);
		log4J.Info("Create New Post - Step 11: Back to Add new post page");
		adminPostViewPage.back(driver);
		log4J.Info("Create New Post - Step 12: Click on view posts icon");
		adminPostSearchPage = adminPostAddEditPage.clickOnViewAllPostsLink();
		log4J.Info("Create New Post - Step 13: Verify title of recently added post with value '" + title + "'");
		verifyEquals(adminPostSearchPage.getRecentlyAddedPostTitle(), title);
		log4J.Info("Create New Post - Step 14: Open user URL");
		userDashboardPage = UserPageGeneratorManager.openUserURL(driver);
		log4J.Info("Create New Post - Step 15: Verify displayed post by post title with value '" + title + "'");
		verifyTrue(userDashboardPage.isTitleDisplayedByPostTitle(title));
		log4J.Info("Create New Post - Step 16: Verify displayed post by post content with value '" + content + "'");
		verifyTrue(userDashboardPage.isContentDisplayedByPostContent(content));
	}

	@Test
	public void TC_02_EditPost() {
		log4J.Info("Pre-condition - Step 01: Open admin URL");
		adminDashboardPage = AdminPageGeneratorManager.openAdminURL(driver);
		log4J.Info("Pre-condition - Step 02: Click on Post sidebar menu");
		adminPostSearchPage = adminDashboardPage.clickOnPostsSidebarMenu();
		log4J.Info("Update Post - Step 01: Hover mouse to title of recently post");
		adminPostSearchPage.hoverMouseToTitleOfRecentlyAddedPost();
		log4J.Info("Update Post - Step 02: Click on edit button of recently post");
		adminPostAddEditPage = adminPostSearchPage.clickOnEditButtonOfRecentlyAddedPost();
		log4J.Info("Update Post - Step 03: Send to add title textbox with value '" + editedTitle + "'");
		adminPostAddEditPage.sendKeyToTitleOfAddEditPost(editedTitle);
		log4J.Info("Update Post - Step 04: Hover and click mouse to first content");
		adminPostAddEditPage.hoverAndClickMouseToContent(1);
		log4J.Info("Update Post - Step 05: Send to add content textbox with value '" + editedContent + "'");
		adminPostAddEditPage.sendKeyToParagraphOfAddEditPost(editedContent);
		log4J.Info("Update Post - Step 06: Click on update button");
		adminPostAddEditPage.clickOnUpdateButton();
		log4J.Info("Update Post - Step 07: Verify post successfully updated message displayed");
		verifyTrue(adminPostAddEditPage.isPostSuccessfullyUpdatedMessageDisplayed());
		log4J.Info("Update Post - Step 08: Click view post button");
		adminPostViewPage = adminPostAddEditPage.clickOnViewUpdatedPostLink();
		log4J.Info("Update Post - Step 09: Verify title with value '" + editedTitle + "'");
		verifyEquals(adminPostViewPage.getViewTitleText(), editedTitle);
		log4J.Info("Update Post - Step 10: Verify content with value '" + editedContent + "'");
		verifyEquals(adminPostViewPage.getViewContentText(), editedContent);
		log4J.Info("Update Post - Step 11: Back to Search post page");
		adminPostViewPage.back(driver);
		log4J.Info("Update Post - Step 12: Click on view posts icon");
		adminPostSearchPage = adminPostAddEditPage.clickOnViewAllPostsLink();
		log4J.Info("Update Post - Step 13: Verify title of recently edited post with value '" + editedTitle + "'");
		verifyEquals(adminPostSearchPage.getRecentlyAddedPostTitle(), editedTitle);
		log4J.Info("Update Post - Step 14: Open user URL");
		userDashboardPage = UserPageGeneratorManager.openUserURL(driver);
		log4J.Info("Update Post - Step 15: Verify displayed post by post title with value '" + editedTitle + "'");
		verifyTrue(userDashboardPage.isTitleDisplayedByPostTitle(editedTitle));
		log4J.Info("Update Post - Step 16: Verify displayed post by post content with value '" + editedContent + "'");
		verifyTrue(userDashboardPage.isContentDisplayedByPostContent(editedContent));
	}

	@Test
	public void TC_03_DeletePost() {
		log4J.Info("Pre-condition - Step 01: Open admin URL");
		adminDashboardPage = AdminPageGeneratorManager.openAdminURL(driver);
		log4J.Info("Pre-condition - Step 02: Click on Post sidebar menu");
		adminPostSearchPage = adminDashboardPage.clickOnPostsSidebarMenu();
		log4J.Info("Delete Post - Step 01: Click on select all posts button");
		adminPostSearchPage.clickOnSelectAllPostsButton();
		log4J.Info("Delete Post - Step 02: Select 'Move to Trash' in bulk actions dropdown");
		adminPostSearchPage.selectMoveToTrashInBulkActions();
		log4J.Info("Delete Post - Step 03: Click on apply buttons to active action");
		adminPostSearchPage.clickOnBulkActionsApplyButton();
		log4J.Info("Delete Post - Step 04: Click on trash link");
		adminPostSearchPage.clickOnTrashLink();
		log4J.Info("Delete Post - Step 05: Click on empty trash");
		adminPostSearchPage.clickOnEmptyTrashButton();
		log4J.Info("Delete Post - Step 06: Verify table message with value '" + noPostMessage + "'");
		verifyEquals(adminPostSearchPage.getNoPostsMessage(), noPostMessage);
		log4J.Info("Delete Post - Step 07: Open user URL");
		userDashboardPage = UserPageGeneratorManager.openUserURL(driver);
		log4J.Info("Delete Post - Step 08: Verify undisplayed post by post title with value '" + editedTitle + "'");
		verifyFalse(userDashboardPage.isTitleDisplayedByPostTitle(editedTitle));
		log4J.Info("Delete Post - Step 09: Verify undisplayed post by post content with value '" + editedContent + "'");
		verifyFalse(userDashboardPage.isContentDisplayedByPostContent(editedContent));
	}

	@AfterClass(alwaysRun = true)
	public void afterClass() {
		driver.close();
	}
}
