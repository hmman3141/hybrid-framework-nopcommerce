package com.nopcommerce.exclude.user;

import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import commons.BaseTest_LocalThread;
import commons.GlobalConstants;
import pageObjects.nopCommerce.user.PageGeneratorManager;
import pageObjects.nopCommerce.user.UserCustomerInfoPageObject;
import pageObjects.nopCommerce.user.UserHomePageObject;
import pageObjects.nopCommerce.user.UserLoginPageObject;
import pageObjects.nopCommerce.user.UserRegisterPageObject;

public class User_05_My_Account_02_Log_Common_LocalThread extends BaseTest_LocalThread {
	private String createdEmail, createdFirstName, createdLastName, password, dayOfBirth, monthOfBirth, yearOfBirth,
			firstName, lastName, email, company;
	private UserRegisterPageObject userRegisterPage;
	private UserHomePageObject userHomePage;
	private UserLoginPageObject userLoginPage;
	private UserCustomerInfoPageObject userCustomerInfoPage;

	@Parameters("Browser")
	@BeforeClass
	public void beforeClass(String browserName) {
		getBrowserDriver(browserName, GlobalConstants.PORTAL_PAGE_URL);
		userHomePage = PageGeneratorManager.getPageGenerator().getUserHomePage(driver.get());

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

		userRegisterPage = userHomePage.clickOnRegisterLink();
		userRegisterPage.sendKeyToFirstNameTextbox(createdFirstName);
		userRegisterPage.sendKeyToLastNameTextbox(createdLastName);
		userRegisterPage.sendKeyToPasswordTextbox(password);
		userRegisterPage.sendKeyToConfirmPasswordTextbox(password);
		userRegisterPage.sendKeyToEmailTextbox(createdEmail);
		userRegisterPage.clickOnRegisterButton();
		Assert.assertEquals(userRegisterPage.getRegisterSuccessMessage(), "Your registration completed");

		userLoginPage = userHomePage.clickOnLoginLink();
		userHomePage = userLoginPage.loginAsUser(createdEmail, password);
		Assert.assertTrue(userHomePage.isAccountLink());
	}

	@Test
	public void TC_01_Customer_Information() {
		userCustomerInfoPage = userHomePage.clickOnMyAccountLink();

		userCustomerInfoPage.clickOnFemaleRadioButton();
		userCustomerInfoPage.sendKeyToFirstNameTextBoxInput(firstName);
		userCustomerInfoPage.sendKeyToLastNameTextBoxInput(lastName);
		userCustomerInfoPage.sendKeyToEmailTextBoxInput(email);
		userCustomerInfoPage.selectItemFromDayCheckbox(dayOfBirth);
		userCustomerInfoPage.selectItemFromMonthCheckbox(monthOfBirth);
		userCustomerInfoPage.selectItemFromYearCheckbox(yearOfBirth);
		userCustomerInfoPage.sendKeyToCompanyTextBoxInput(company);
		userCustomerInfoPage.clickOnSaveButton();

		Assert.assertEquals(userCustomerInfoPage.getTextFromFirstNameTextBoxInput(), firstName);
		Assert.assertEquals(userCustomerInfoPage.getTextFromLastNameTextBoxInput(), lastName);
		Assert.assertEquals(userCustomerInfoPage.getTextFromEmailNameTextBoxInput(), email);
		Assert.assertEquals(userCustomerInfoPage.getTextFromCompanyNameTextBoxInput(), company);
		Assert.assertEquals(userCustomerInfoPage.getTextFromDayOfBirthDropdown(), dayOfBirth);
		Assert.assertEquals(userCustomerInfoPage.getTextFromMonthOfBirthDropdown(), monthOfBirth);
		Assert.assertEquals(userCustomerInfoPage.getTextFromYearOfBirthDropdown(), yearOfBirth);
	}

	@AfterClass(alwaysRun = true)
	public void afterClass() {
		driver.get().close();
		driver.remove();
	}
}
