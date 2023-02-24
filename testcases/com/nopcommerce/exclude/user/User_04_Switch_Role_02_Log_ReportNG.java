package com.nopcommerce.exclude.user;

import static reportConfig.TestListener.log4J;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import commons.BaseTest;
import commons.GlobalConstants;
import pageObjects.nopCommerce.admin.AdminDashboardPageObject;
import pageObjects.nopCommerce.admin.AdminLoginPageObject;
import pageObjects.nopCommerce.user.PageGeneratorManager;
import pageObjects.nopCommerce.user.UserHomePageObject;
import pageObjects.nopCommerce.user.UserLoginPageObject;
import pageObjects.nopCommerce.user.UserRegisterPageObject;

public class User_04_Switch_Role_02_Log_ReportNG extends BaseTest {
	private WebDriver driver;
	private String userEmail, userFirstName, userLastName, userPassword, adminEmail, adminPassword;
	private UserRegisterPageObject userRegisterPageObject;
	private UserHomePageObject userHomePage;
	private UserLoginPageObject userLoginPage;
	private AdminLoginPageObject adminLoginPage;
	private AdminDashboardPageObject adminDashboardPage;

	@Parameters("Browser")
	@BeforeClass
	public void beforeClass(String browserName) {
		driver = getBrowserDriver(browserName);
		driver.get(GlobalConstants.PORTAL_PAGE_URL);
		userHomePage = PageGeneratorManager.getPageGenerator().getUserHomePage(driver);

		userEmail = getRandomNumber() + "@test.com";
		userPassword = "password";
		userFirstName = "First name";
		userLastName = "Last name";

		adminEmail = "admin@yourstore.com";
		adminPassword = "admin";
	}

	@Test
	public void Role_01_User() {
		log4J.Info("Role User - Step 01: Click on register link");
		userRegisterPageObject = userHomePage.clickOnRegisterLink();

		log4J.Info("Role User - Step 02: Enter to first name textbox with value '" + userFirstName + "'");
		userRegisterPageObject.sendKeyToFirstNameTextbox(userFirstName);
		log4J.Info("Role User - Step 03: Enter to last name textbox with value '" + userLastName + "'");
		userRegisterPageObject.sendKeyToLastNameTextbox(userLastName);
		log4J.Info("Role User - Step 04: Enter to password textbox with value '" + userPassword + "'");
		userRegisterPageObject.sendKeyToPasswordTextbox(userPassword);
		log4J.Info("Role User - Step 05: Enter to confirm password textbox with value '" + userPassword + "'");
		userRegisterPageObject.sendKeyToConfirmPasswordTextbox(userPassword);
		log4J.Info("Role User - Step 06: Enter to email textbox with value '" + userEmail + "'");
		userRegisterPageObject.sendKeyToEmailTextbox(userEmail);
		log4J.Info("Role User - Step 07: Click on register button");
		userRegisterPageObject.clickOnRegisterButton();

		log4J.Info("Role User - Step 08: Verify successfully register message");
		verifyEquals(userRegisterPageObject.getRegisterSuccessMessage(), "Your registration completed");

		log4J.Info("Role User - Step 09: Click on log in link");
		userLoginPage = userHomePage.clickOnLoginLink();
		log4J.Info("Role User - Step 10: Log in with giving log4J.Information: " + userEmail + " / " + userPassword);
		userHomePage = userLoginPage.loginAsUser(userEmail, userPassword);

		log4J.Info("Role User - Step 11: Verify account is link");
		verifyTrue(userHomePage.isAccountLink());

		log4J.Info("Role User - Step 12: Click on log out link");
		userHomePage = userHomePage.clickOnLogoutAtUserPage(driver);
		log4J.Info("Role User - Step 13: Open admin URL");
		userHomePage.openURL(driver, GlobalConstants.ADMIN_PAGE_URL);
		adminLoginPage = PageGeneratorManager.getPageGenerator().getAdminLoginPage(driver);

		log4J.Info("Role User - Step 14: Login with admin log4J.Information: " + adminEmail + " / " + adminPassword);
		adminDashboardPage = adminLoginPage.loginAsAdmin(adminEmail, adminPassword);
		
		log4J.Info("Role User - Step 15: Verify dashboard header is displayed");
		verifyTrue(adminDashboardPage.isDashboardHeaderDisplayed());
		log4J.Info("Role User - Step 16: Click on log out link");
		adminLoginPage = adminDashboardPage.clickOnLogoutAtAdminPage(driver);
	}

	@AfterClass
	public void afterClass() {
		driver.close();
	}
}
