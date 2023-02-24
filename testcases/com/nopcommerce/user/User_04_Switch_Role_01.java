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

public class User_04_Switch_Role_01 extends BaseTest {
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
		userRegisterPageObject = userHomePage.clickOnRegisterLink();

		userRegisterPageObject.sendKeyToFirstNameTextbox(userFirstName);
		userRegisterPageObject.sendKeyToLastNameTextbox(userLastName);
		userRegisterPageObject.sendKeyToPasswordTextbox(userPassword);
		userRegisterPageObject.sendKeyToConfirmPasswordTextbox(userPassword);
		userRegisterPageObject.sendKeyToEmailTextbox(userEmail);
		userRegisterPageObject.clickOnRegisterButton();

		Assert.assertEquals(userRegisterPageObject.getRegisterSuccessMessage(), "Your registration completed");
		userRegisterPageObject.clickOnLogoutButton();

		userLoginPage = userHomePage.clickOnLoginLink();
		userHomePage = userLoginPage.loginAsUser(userEmail, userPassword);

		Assert.assertTrue(userHomePage.isAccountLink());

		userHomePage = userHomePage.clickOnLogoutAtUserPage(driver);
		userHomePage.openURL(driver, GlobalConstants.ADMIN_PAGE_URL);
		adminLoginPage = PageGeneratorManager.getPageGenerator().getAdminLoginPage(driver);

		adminDashboardPage = adminLoginPage.loginAsAdmin(adminEmail, adminPassword);
		Assert.assertTrue(adminDashboardPage.isDashboardHeaderDisplayed());
		adminLoginPage = adminDashboardPage.clickOnLogoutAtAdminPage(driver);
	}

	@Test
	public void Role_02_Admin() {
		userHomePage.openURL(driver, GlobalConstants.ADMIN_PAGE_URL);
		adminLoginPage = PageGeneratorManager.getPageGenerator().getAdminLoginPage(driver);

		adminDashboardPage = adminLoginPage.loginAsAdmin(adminEmail, adminPassword);
		Assert.assertTrue(adminDashboardPage.isDashboardHeaderDisplayed());
		
		adminLoginPage = adminDashboardPage.clickOnLogoutAtAdminPage(driver);
		adminLoginPage.openURL(driver, GlobalConstants.PORTAL_PAGE_URL);
		userLoginPage = userHomePage.clickOnLoginLink();
		userHomePage = userLoginPage.loginAsUser(userEmail, userPassword);

		Assert.assertTrue(userHomePage.isAccountLink());

	}

	@AfterClass
	public void afterClass() {
		driver.close();
	}
}
