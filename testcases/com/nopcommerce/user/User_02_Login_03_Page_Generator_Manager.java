package com.nopcommerce.user;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import commons.BaseTest;
import commons.GlobalConstants;
import pageObjects.nopCommerce.user.UserHomePageObject;
import pageObjects.nopCommerce.user.UserLoginPageObject;
import pageObjects.nopCommerce.user.PageGeneratorManager;

public class User_02_Login_03_Page_Generator_Manager extends BaseTest {
	private WebDriver driver;
	private UserLoginPageObject loginPageObject;
	private UserHomePageObject homePageObject;
	private String email, invalidEmail, password;

	@Parameters("Browser")
	@BeforeClass
	public void beforeClass(String browserName) {
		driver = getBrowserDriver(browserName);
		driver.get(GlobalConstants.PORTAL_PAGE_URL);
		homePageObject = PageGeneratorManager.getUserHomePage(driver);
		email = getRandomNumber() + "@test.com";
		invalidEmail = "abc@test/com";
		password = "password";
	}

	@Test
	public void TC_01_LoginWithEmptyData() {
		loginPageObject = homePageObject.clickOnLoginLink();

		loginPageObject.clickOnLoginButton();

		Assert.assertEquals(loginPageObject.getErrorMessageAtEmailInput(), "Please enter your email");
	}

	@Test
	public void TC_02_LoginWithInvalidEmail() {
		loginPageObject = homePageObject.clickOnLoginLink();

		loginPageObject.sendKeyToEmailTextbox(invalidEmail);
		loginPageObject.clickOnLoginButton();

		Assert.assertEquals(loginPageObject.getErrorMessageAtEmailInput(), "Wrong email");
	}

	@Test
	public void TC_03_LoginWithNonExistentEmail() {
		loginPageObject = homePageObject.clickOnLoginLink();

		loginPageObject.sendKeyToEmailTextbox(email);
		loginPageObject.sendKeyToPasswordTextbox(password);
		loginPageObject.clickOnLoginButton();

		Assert.assertEquals(loginPageObject.getErrorMessageByNonExistentEmail(),
				"Login was unsuccessful. Please correct the errors and try again.\n" + "No customer account found");
	}

	@Test
	public void TC_04_LoginWithBlankPassword() {
		loginPageObject = homePageObject.clickOnLoginLink();

		loginPageObject.sendKeyToEmailTextbox(email);
		loginPageObject.clickOnLoginButton();

		Assert.assertEquals(loginPageObject.getErrorMessageByNonExistentEmail(),
				"Login was unsuccessful. Please correct the errors and try again.\n" + "No customer account found");
	}

	@AfterClass
	public void afterClass() {
		driver.close();
	}
}
