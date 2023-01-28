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

import static reportConfig.TestListener.log4J;

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
		
		log4J.Info("Register - Step 01: Click on register link");
		userRegisterPageObject = userHomePage.clickOnRegisterLink();

		log4J.Info("Register - Step 02: Enter to first name textbox with value '" + userFirstName + "'");
		userRegisterPageObject.sendKeyToFirstNameTextbox(userFirstName);
		log4J.Info("Register - Step 03: Enter to last name textbox with value '" + userLastName + "'");
		userRegisterPageObject.sendKeyToLastNameTextbox(userLastName);
		log4J.Info("Register - Step 04: Enter to password textbox with value '" + userPassword + "'");
		userRegisterPageObject.sendKeyToPasswordTextbox(userPassword);
		log4J.Info("Register - Step 05: Enter to confirm password textbox with value '" + userPassword + "'");
		userRegisterPageObject.sendKeyToConfirmPasswordTextbox(userPassword);
		log4J.Info("Register - Step 06: Enter to email textbox with value '" + userEmail + "'");
		userRegisterPageObject.sendKeyToEmailTextbox(userEmail);
		log4J.Info("Register - Step 07: Click on register button");
		userRegisterPageObject.clickOnRegisterButton();

		log4J.Info("Register - Step 08: Verify successfully register message");
		verifyEquals(userRegisterPageObject.getRegisterSuccessMessage(), "Your registration completed");

		log4J.Info("Register - Step 09: Click on log in link");
		userHomePage.clickOnLoginLink();
		
		TestListener.endTest();
		
		closeBrowserDriver();
	}
}
