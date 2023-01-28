package com.nopcommerce.exclude.user;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.nopcommerce.common.Common_01_Register_New_Account;

import commons.BaseTest;
import commons.GlobalConstants;
import pageObjects.nopCommerce.user.PageGeneratorManager;
import pageObjects.nopCommerce.user.UserCustomerInfoPageObject;
import pageObjects.nopCommerce.user.UserHomePageObject;
import pageObjects.nopCommerce.user.UserLoginPageObject;
import reportConfig.TestListener;

import static reportConfig.TestListener.log4J;

public class User_05_My_Account_02_Log_Common extends BaseTest {
	private WebDriver driver;
	private String dayOfBirth, monthOfBirth, yearOfBirth, firstName, lastName, email, company;
	private UserHomePageObject userHomePage;
	private UserLoginPageObject userLoginPage;
	private UserCustomerInfoPageObject userCustomerInfoPage;

	@Parameters("Browser")
	@BeforeClass
	public void beforeClass(String browserName) {
		driver = getBrowserDriver(browserName);
		driver.get(GlobalConstants.PORTAL_PAGE_URL);
		userHomePage = PageGeneratorManager.getUserHomePage(driver);

		String userEmail = Common_01_Register_New_Account.userEmail;
		String userPassword = Common_01_Register_New_Account.userPassword;
		firstName = "First name";
		lastName = "Last name";
		email = "automationfc.vn@gmail.com";
		dayOfBirth = "1";
		monthOfBirth = "January";
		yearOfBirth = "1999";
		company = "Automation FC";
		
		TestListener.startTest("Pre-condition - Login", "Login to the website");

		log4J.Info("Login - Step 01: Click on login link");
		userLoginPage = userHomePage.clickOnLoginLink();
		log4J.Info("Login - Step 02: Login with information: " + userEmail + "/" + userPassword);
		userHomePage = userLoginPage.loginAsUser(userEmail, userPassword);

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
		closeBrowserDriver();
	}
}
