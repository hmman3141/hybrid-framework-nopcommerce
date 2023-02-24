package com.nopcommerce.exclude.user;

import static reportConfig.TestListener.log4J;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import commons.BaseTest;
import commons.GlobalConstants;
import commons.driver.SaucelabFactory;
import pageObjects.nopCommerce.user.PageGeneratorManager;
import pageObjects.nopCommerce.user.UserCustomerInfoPageObject;
import pageObjects.nopCommerce.user.UserHomePageObject;
import pageObjects.nopCommerce.user.UserLoginPageObject;
import pageObjects.nopCommerce.user.UserRegisterPageObject;
import reportConfig.TestListener;

public class User_05_My_Account_03_Selenium_Saucelab extends BaseTest {
	private WebDriver driver;
	private String createdEmail, createdFirstName, createdLastName, password, dayOfBirth, monthOfBirth,
			yearOfBirth, firstName, lastName, email, company;
	private UserRegisterPageObject userRegisterPage;
	private UserHomePageObject userHomePage;
	private UserLoginPageObject userLoginPage;
	private UserCustomerInfoPageObject userCustomerInfoPage;

	@Parameters({ "Browser" })
	@BeforeClass
	public void beforeClass(String browserName) {
		driver = new SaucelabFactory(GlobalConstants.SAUCELAB_BUILDID, GlobalConstants.SAUCELAB_EMAIL,
				GlobalConstants.SAUCELAB_ID).getBrowserDriver(browserName, GlobalConstants.PORTAL_PAGE_URL);
		userHomePage = PageGeneratorManager.getUserHomePage(driver);

		createdEmail = getRandomNumber() + "@test.com";
		createdFirstName = "First name";
		createdLastName = "Last name";
		email = "automationfc" + createdEmail;
		password = "password";
		dayOfBirth = "1";
		monthOfBirth = "January";
		yearOfBirth = "1999";
		firstName = "Automation";
		lastName = "FC";
		company = "Automation FC";
		
		TestListener.startTest("Pre-condition - Register", "Register new account");

		log4J.Info("Register - Step 01: Click on register link");
		userRegisterPage = userHomePage.clickOnRegisterLink();

		log4J.Info("Register - Step 02: Send key to first name textbox");
		userRegisterPage.sendKeyToFirstNameTextbox(createdFirstName);
		log4J.Info("Register - Step 03: Send key to last name textbox");
		userRegisterPage.sendKeyToLastNameTextbox(createdLastName);
		log4J.Info("Register - Step 04: Send key to password textbox");
		userRegisterPage.sendKeyToPasswordTextbox(password);
		log4J.Info("Register - Step 05: Send key to confirm password textbox");
		userRegisterPage.sendKeyToConfirmPasswordTextbox(password);
		log4J.Info("Register - Step 06: Send key to email textbox");
		userRegisterPage.sendKeyToEmailTextbox(createdEmail);
		log4J.Info("Register - Step 07: Click on register button");
		userRegisterPage.clickOnRegisterButton();

		log4J.Info("Register - Step 08: Verify successful register message");
		verifyEquals(userRegisterPage.getRegisterSuccessMessage(), "Your registration completed");

		TestListener.endTest();
		
		TestListener.startTest("Pre-condition - Login", "Login to the website");

		log4J.Info("Login - Step 01: Click on login link");
		userLoginPage = userHomePage.clickOnLoginLink();
		log4J.Info("Login - Step 02: Login with information: " + createdEmail + "/" + password);
		userHomePage = userLoginPage.loginAsUser(createdEmail, password);

		log4J.Info("Login - Step 03: Verify account is linked");
		verifyTrue(userHomePage.isAccountLink());

		TestListener.endTest();
	}

	@Test
	public void TC_01_Customer_Information() {
		log4J.Info("User-Customer Information - Step 01: Click on my account link");
		userCustomerInfoPage = userHomePage.clickOnMyAccountLink();
		verifyTrue(userCustomerInfoPage.isCustomerInfoDisplay());

		log4J.Info("User-Customer Information - Step 02: Click on female radio button");
		userCustomerInfoPage.clickOnFemaleRadioButton();
		log4J.Info("User-Customer Information - Step 03: Send key to first name test box with value: " + firstName);
		userCustomerInfoPage.sendKeyToFirstNameTextBoxInput(firstName);
		log4J.Info("User-Customer Information - Step 04: Send key to last name test box with value: " + lastName);
		userCustomerInfoPage.sendKeyToLastNameTextBoxInput(lastName);
		log4J.Info("User-Customer Information - Step 05: Send key to email test box with value: " + email);
		userCustomerInfoPage.sendKeyToEmailTextBoxInput(email);
		log4J.Info("User-Customer Information - Step 06: Select item from day checkbox with value: " + dayOfBirth);
		userCustomerInfoPage.selectItemFromDayCheckbox(dayOfBirth);
		log4J.Info("User-Customer Information - Step 07: Select item from month checkbox with value: " + monthOfBirth);
		userCustomerInfoPage.selectItemFromMonthCheckbox(monthOfBirth);
		log4J.Info("User-Customer Information - Step 08: Select item from year checkbox with value: " + yearOfBirth);
		userCustomerInfoPage.selectItemFromYearCheckbox(yearOfBirth);
		log4J.Info("User-Customer Information - Step 09: Send key to company test box with value: " + company);
		userCustomerInfoPage.sendKeyToCompanyTextBoxInput(company);
		log4J.Info("User-Customer Information - Step 10: Click on save button");
		userCustomerInfoPage.clickOnSaveButton();

		log4J.Info("User-Customer Information - Step 11: Verify first name with value: " + firstName);
		verifyEquals(userCustomerInfoPage.getTextFromFirstNameTextBoxInput(), firstName);
		log4J.Info("User-Customer Information - Step 12: Verify first name with value: " + lastName);
		verifyEquals(userCustomerInfoPage.getTextFromLastNameTextBoxInput(), lastName);
		log4J.Info("User-Customer Information - Step 13: Verify first name with value: " + email);
		verifyEquals(userCustomerInfoPage.getTextFromEmailNameTextBoxInput(), email);
		log4J.Info("User-Customer Information - Step 14: Verify first name with value: " + company);
		verifyEquals(userCustomerInfoPage.getTextFromCompanyNameTextBoxInput(), company);
		log4J.Info("User-Customer Information - Step 15: Verify first name with value: " + dayOfBirth);
		verifyEquals(userCustomerInfoPage.getTextFromDayOfBirthDropdown(), dayOfBirth);
		log4J.Info("User-Customer Information - Step 16: Verify first name with value: " + monthOfBirth);
		verifyEquals(userCustomerInfoPage.getTextFromMonthOfBirthDropdown(), monthOfBirth);
		log4J.Info("User-Customer Information - Step 17: Verify first name with value: " + yearOfBirth);
		verifyEquals(userCustomerInfoPage.getTextFromYearOfBirthDropdown(), yearOfBirth);
	}

	@AfterClass(alwaysRun = true)
	public void afterClass() {
		driver.close();
	}
}
