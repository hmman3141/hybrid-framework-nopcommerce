package com.nopcommerce.user;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import commons.BaseTest;
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
		driver.get("https://demo.nopcommerce.com/");
		homePageObject = PageGeneratorManager.getUserHomePage(driver);
		email = getRandomNumber() + "@test.com";
		invalidEmail = "abc@test/com";
		password = "password";
	}

	@Test
	public void TC_01_LoginWithEmptyData() {
		loginPageObject = homePageObject.clickToLoginLink();

		loginPageObject.clickToLoginButton();

		Assert.assertEquals(loginPageObject.getErrorMessageAtEmailInput(), "Please enter your email");
	}

	@Test
	public void TC_02_LoginWithInvalidEmail() {
		loginPageObject = homePageObject.clickToLoginLink();

		loginPageObject.sendKeyToEmailTextbox(invalidEmail);
		loginPageObject.clickToLoginButton();

		Assert.assertEquals(loginPageObject.getErrorMessageAtEmailInput(), "Wrong email");
	}

	@Test
	public void TC_03_LoginWithNonExistentEmail() {
		loginPageObject = homePageObject.clickToLoginLink();

		loginPageObject.sendKeyToEmailTextbox(email);
		loginPageObject.sendKeyToPasswordTextbox(password);
		loginPageObject.clickToLoginButton();

		Assert.assertEquals(loginPageObject.getErrorMessageByNonExistentEmail(),
				"Login was unsuccessful. Please correct the errors and try again.\n" + "No customer account found");
	}

	@Test
	public void TC_04_LoginWithBlankPassword() {
		loginPageObject = homePageObject.clickToLoginLink();

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
