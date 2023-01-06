package com.nopcommerce.common;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;

import commons.BaseTest;
import commons.GlobalConstants;
import pageObjects.nopCommerce.user.PageGeneratorManager;
import pageObjects.nopCommerce.user.UserHomePageObject;
import pageObjects.nopCommerce.user.UserRegisterPageObject;
import reportConfig.TestListener;

public class Common_01_Register_New_Account extends BaseTest {
	private WebDriver driver;
	private String userFirstName, userLastName;
	public static String userEmail, userPassword;

	private UserRegisterPageObject userRegisterPageObject;
	private UserHomePageObject userHomePage;

	@Parameters("Browser")
	@BeforeTest
	public void beforeTest(String browserName) {
		driver = getBrowserDriver(browserName);
		driver.get(GlobalConstants.PORTAL_PAGE_URL);
		userHomePage = PageGeneratorManager.getUserHomePage(driver);

		userEmail = getRandomNumber() + "@test.com";
		userPassword = "password";
		userFirstName = "First name";
		userLastName = "Last name";
		
		TestListener.startTest("Pre-condition - Create account", "Create new account");
		
		Info("Register - Step 01: Click to register link");
		userRegisterPageObject = userHomePage.clickToRegisterLink();

		Info("Register - Step 02: Enter to first name textbox with value '" + userFirstName + "'");
		userRegisterPageObject.sendKeyToFirstNameTextbox(userFirstName);
		Info("Register - Step 03: Enter to last name textbox with value '" + userLastName + "'");
		userRegisterPageObject.sendKeyToLastNameTextbox(userLastName);
		Info("Register - Step 04: Enter to password textbox with value '" + userPassword + "'");
		userRegisterPageObject.sendKeyToPasswordTextbox(userPassword);
		Info("Register - Step 05: Enter to confirm password textbox with value '" + userPassword + "'");
		userRegisterPageObject.sendKeyToConfirmPasswordTextbox(userPassword);
		Info("Register - Step 06: Enter to email textbox with value '" + userEmail + "'");
		userRegisterPageObject.sendKeyToEmailTextbox(userEmail);
		Info("Register - Step 07: Click to register button");
		userRegisterPageObject.clickToRegisterButton();

		Info("Register - Step 08: Verify successfully register message");
		verifyEquals(userRegisterPageObject.getRegisterSuccessMessage(), "Your registration completed");

		Info("Register - Step 09: Click to log in link");
		userHomePage.clickToLoginLink();
		
		TestListener.endTest();
		
		closeBrowserDriver();
	}
}
