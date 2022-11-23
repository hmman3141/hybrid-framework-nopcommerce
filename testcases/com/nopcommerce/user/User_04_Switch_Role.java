package com.nopcommerce.user;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
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

public class User_04_Switch_Role extends BaseTest {
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
		driver.get("https://demo.nopcommerce.com/");
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
		userRegisterPageObject = userHomePage.clickToRegisterLink();

		userRegisterPageObject.sendKeyToFirstNameTextbox(userFirstName);
		userRegisterPageObject.sendKeyToLastNameTextbox(userLastName);
		userRegisterPageObject.sendKeyToPasswordTextbox(userPassword);
		userRegisterPageObject.sendKeyToConfirmPasswordTextbox(userPassword);
		userRegisterPageObject.sendKeyToEmailTextbox(userEmail);
		userRegisterPageObject.clickToRegisterButton();

		Assert.assertEquals(userRegisterPageObject.getRegisterSuccessMessage(), "Your registration completed");
		userRegisterPageObject.clickToLogoutButton();

		userLoginPage = userHomePage.clickToLoginLink();
		userHomePage = userLoginPage.loginAsUser(userEmail, userPassword);

		Assert.assertTrue(userHomePage.isAccountLink());

		userHomePage = userHomePage.clickToLogoutAtUserPage(driver);
		userHomePage.openURL(driver, GlobalConstants.ADMIN_PAGE_URL);
		adminLoginPage = PageGeneratorManager.getAdminLoginPage(driver);

		adminDashboardPage = adminLoginPage.loginAsAdmin(adminEmail, adminPassword);
		Assert.assertTrue(adminDashboardPage.isDashboardHeaderDisplayed());
		adminLoginPage = adminDashboardPage.clickToLogoutAtAdminPage(driver);
	}

	@Test
	public void Role_02_Admin() {
		userHomePage.openURL(driver, GlobalConstants.ADMIN_PAGE_URL);
		adminLoginPage = PageGeneratorManager.getAdminLoginPage(driver);

		adminDashboardPage = adminLoginPage.loginAsAdmin(adminEmail, adminPassword);
		Assert.assertTrue(adminDashboardPage.isDashboardHeaderDisplayed());
		
		adminLoginPage = adminDashboardPage.clickToLogoutAtAdminPage(driver);
		adminLoginPage.openURL(driver, GlobalConstants.PORTAL_PAGE_URL);
		userLoginPage = userHomePage.clickToLoginLink();
		userHomePage = userLoginPage.loginAsUser(userEmail, userPassword);

		Assert.assertTrue(userHomePage.isAccountLink());

	}

	@AfterClass
	public void afterClass() {
		driver.close();
	}
}
