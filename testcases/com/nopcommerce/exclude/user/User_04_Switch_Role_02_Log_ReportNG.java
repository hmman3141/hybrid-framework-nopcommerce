package com.nopcommerce.exclude.user;

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
		userHomePage = PageGeneratorManager.getUserHomePage(driver);

		userEmail = getRandomNumber() + "@test.com";
		userPassword = "password";
		userFirstName = "First name";
		userLastName = "Last name";

		adminEmail = "admin@yourstore.com";
		adminPassword = "admin";
	}

	@Test
	public void Role_01_User() {
		log.info("Role User - Step 01: Click to register link");
		userRegisterPageObject = userHomePage.clickToRegisterLink();

		log.info("Role User - Step 02: Enter to first name textbox with value '" + userFirstName + "'");
		userRegisterPageObject.sendKeyToFirstNameTextbox(userFirstName);
		log.info("Role User - Step 03: Enter to last name textbox with value '" + userLastName + "'");
		userRegisterPageObject.sendKeyToLastNameTextbox(userLastName);
		log.info("Role User - Step 04: Enter to password textbox with value '" + userPassword + "'");
		userRegisterPageObject.sendKeyToPasswordTextbox(userPassword);
		log.info("Role User - Step 05: Enter to confirm password textbox with value '" + userPassword + "'");
		userRegisterPageObject.sendKeyToConfirmPasswordTextbox(userPassword);
		log.info("Role User - Step 06: Enter to email textbox with value '" + userEmail + "'");
		userRegisterPageObject.sendKeyToEmailTextbox(userEmail);
		log.info("Role User - Step 07: Click to register button");
		userRegisterPageObject.clickToRegisterButton();

		verifyEquals(userRegisterPageObject.getRegisterSuccessMessage(), "Your registration completed");

		log.info("Role User - Step 08: Click to log in link");
		userLoginPage = userHomePage.clickToLoginLink();
		log.info("Role User - Step 09: Log in with giving information: " + userEmail + " / " + userPassword);
		userHomePage = userLoginPage.loginAsUser(userEmail, userPassword);

		verifyTrue(userHomePage.isAccountLink());

		log.info("Role User - Step 10: Click to log out link");
		userHomePage = userHomePage.clickToLogoutAtUserPage(driver);
		log.info("Role User - Step 11: Open admin URL");
		userHomePage.openURL(driver, GlobalConstants.ADMIN_PAGE_URL);
		adminLoginPage = PageGeneratorManager.getAdminLoginPage(driver);

		log.info("Role User - Step 12: Login with admin information: " + adminEmail + " / " + adminPassword);
		adminDashboardPage = adminLoginPage.loginAsAdmin(adminEmail, adminPassword);
		verifyTrue(adminDashboardPage.isDashboardHeaderDisplayed());
		log.info("Role User - Step 13: Click to log out link");
		adminLoginPage = adminDashboardPage.clickToLogoutAtAdminPage(driver);
	}

	@AfterClass
	public void afterClass() {
		driver.close();
	}
}
