package com.nopcommerce.exclude.user;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import commons.BaseTest;
import commons.GlobalConstants;
import pageObjects.nopCommerce.user.UserHomePageObject_BaseFactory;
import pageObjects.nopCommerce.user.UserLoginPageObject_BaseFactory;

public class User_02_Login_02_Page_Factory extends BaseTest {
	private WebDriver driver;
	private UserLoginPageObject_BaseFactory loginPageObject;
	private UserHomePageObject_BaseFactory homePageObject;
	private String email, invalidEmail, password;

	@Parameters("Browser")
	@BeforeClass
	public void beforeClass(String browserName) {
		driver = getBrowserDriver(browserName);
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.get(GlobalConstants.PORTAL_PAGE_URL);
		loginPageObject = new UserLoginPageObject_BaseFactory(driver);
		homePageObject = new UserHomePageObject_BaseFactory(driver);
		email = getRandomNumber() + "@test.com";
		invalidEmail = "abc@test/com";
		password = "password";
	}

	@Test
	public void TC_01_LoginWithEmptyData() {
		homePageObject.clickToLoginLink();
		loginPageObject.clickToLoginButton();

		Assert.assertEquals(loginPageObject.getErrorMessageAtEmailInput(), "Please enter your email");
	}

	@Test
	public void TC_02_LoginWithInvalidEmail() {
		homePageObject.clickToLoginLink();
		loginPageObject.sendKeyToEmailTextbox(invalidEmail);
		loginPageObject.clickToLoginButton();

		Assert.assertEquals(loginPageObject.getErrorMessageAtEmailInput(), "Wrong email");
	}

	@Test
	public void TC_03_LoginWithNonExistentEmail() {
		homePageObject.clickToLoginLink();
		loginPageObject.sendKeyToEmailTextbox(email);
		loginPageObject.sendKeyToPasswordTextbox(password);
		loginPageObject.clickToLoginButton();

		Assert.assertEquals(loginPageObject.getErrorMessageByNonExistentEmail(),
				"Login was unsuccessful. Please correct the errors and try again.\n" + "No customer account found");
	}

	@Test
	public void TC_04_LoginWithBlankPassword() {
		homePageObject.clickToLoginLink();
		loginPageObject.sendKeyToEmailTextbox(email);
		loginPageObject.clickToLoginButton();

		Assert.assertEquals(loginPageObject.getErrorMessageByNonExistentEmail(),
				"Login was unsuccessful. Please correct the errors and try again.\n" + "No customer account found");
	}

	@AfterClass
	public void afterClass() {
		driver.close();
	}
}
